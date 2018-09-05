package com.softfz.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.softfz.config.DataSourceConfig;
import com.softfz.dao.FlightDAO;
import com.softfz.dao.NetDealerDAO;
import com.softfz.dao.SaleRecordDAO;
import com.softfz.dao.TicketStoreDAO;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.BounceRecord;
import com.softfz.model.Flight;
import com.softfz.model.FlightSaleTotal;
import com.softfz.model.NetDealer;
import com.softfz.model.Oiltax;
import com.softfz.model.PageModel;
import com.softfz.model.SaleRecord;
import com.softfz.model.SaleTotal;
import com.softfz.model.SalesTicketInfoShow;
import com.softfz.model.TicketStore;

public class NetServiceImpl extends UnicastRemoteObject implements INetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NetDealerDAO netDealerDAO;
	private FlightDAO flightDAO;
	private SaleRecordDAO saleRecordDAO;
	private TicketStoreDAO ticketStoreDAO;

	public NetServiceImpl() throws RemoteException {
		super();
		netDealerDAO=new NetDealerDAO();
		flightDAO=new FlightDAO();
		saleRecordDAO=new SaleRecordDAO();
		ticketStoreDAO=new TicketStoreDAO();
	}
	
	@Override
	public void showMsgToServer(String str) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public NetDealer login(String netcode, String password)
			throws RemoteException {
		return netDealerDAO.login(netcode,password);
	}

	@Override
	public void checkIsLock(String netcode) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public Boolean updateNetClientPwd(int netid, String passwordOld,
			String passwordNew) throws RemoteException {
		// TODO Auto-generated method stub
		int result=netDealerDAO.updateNetClientPwd(netid,passwordOld,passwordNew);
		if (result>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageModel<Flight> queryFlights(String fromCity, String toCity,
			Date planDate, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		return flightDAO.queryFlights(fromCity, toCity,planDate, currentPage, pageSize);
	}

	@Override
	public Flight queryFlights(int flightid) throws RemoteException {
		// TODO Auto-generated method stub
		
		return flightDAO.queryFlights(flightid);
	}

	@Override
	public String saleTicket(SaleRecord record) throws RemoteException {
		// TODO Auto-generated method stub
		//如果并发量过大就会出错，可能同时添加两条一样 的saleid
		//一种方法是先获取当前最大saleid，可以保证salerecord与salelog的saleid一致
		//另一种是让salerecord自动添加saleid，再获取最大的saleid，添加到salelog中
		int result= saleRecordDAO.saleTicket(record);//选第一种，这一步包含两个表的操作，让两个语句尽量靠近。
		if (result>0) {
			int storeid=record.getStoreid();
			ticketStoreDAO.storenumDown(storeid); //剩余票数减一
			return "OK";
		}else {
			return "出错了";
		}
	}

	@Override
	public String cancelTicket(BounceRecord record) throws RemoteException {
		// TODO Auto-generated method stub
		JdbcOperator jdbcOperator= new JdbcOperatorImpl(DataSourceConfig.getDataSource());
		String sql="UPDATE airticket.salerecord SET SALESTATE='1' WHERE SALEID = ?;";
		jdbcOperator.queryForInt(sql, record.getSaleid());
		
		sql="INSERT INTO airticket.bouncerecord ( SALEID, NETID, IDCARD, CUSTTEL, REASON, MONEY) \r\n" + 
				"VALUES (?, ?, ?, ?, ?, ?)";
		jdbcOperator.queryForInt(sql, record.getSaleid(),record.getNetid(),record.getIdcard(),record.getCusttel(),record.getReason(),record.getMoney());
		
//		这段是使原来的机票座位加一回来。
//		String salerecordstarttime=(String) jdbcOperator.queryOneValue("select STARTTIME from airticket.salerecord WHERE SALEID = ?", record.getSaleid());
//		String a[]=salerecordstarttime.split(" ");
//		salerecordstarttime=a[0]+" 00:00:00";
//		
//		ticketStoreDAO.storenumUp((Integer) jdbcOperator.queryOneValue("select storeid from airticket.ticketstore \r\n" + 
//				"where flightid in (select flightid from airticket.salerecord WHERE SALEID = ?) \r\n" + 
//				"and ticketdatetime = ?", record.getSaleid(),salerecordstarttime));
		
		sql="INSERT INTO airticket.salelog (SALEID, NETID, OPERATORTYPE) \r\n" + 
				"VALUES ( ?, ?, '1')";
		jdbcOperator.queryForInt(sql, record.getSaleid(),record.getNetid());
		return null;
	}

	@Override
	public String changeTicketDate(int recordid, Date newPlanDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String zhuanqiandate=formatter.format(newPlanDate);
		JdbcOperator jdbcOperator= new JdbcOperatorImpl(DataSourceConfig.getDataSource());
		String sql="SELECT * FROM airticket.ticketstore where flightid in (select FLIGHTID from airticket.salerecord WHERE SALEID = ?) and ticketdatetime=?; ";
		TicketStore ticketStore=(TicketStore) jdbcOperator.queryForJavaBean(sql, TicketStore.class, recordid,zhuanqiandate);
		if (ticketStore==null) {
			return "该新出行日期没有飞机，请重新选择出行日期。";
		}
		sql="UPDATE airticket.salerecord SET SALESTATE='2' WHERE SALEID = ?;";
		jdbcOperator.queryForInt(sql, recordid);
		
		
//		ticketStoreDAO.storenumUp(recordid);
		
		sql="select * from airticket.salerecord WHERE SALEID = ?;";
		SaleRecord saleRecord=(SaleRecord) jdbcOperator.queryForJavaBean(sql, SaleRecord.class, recordid);
		
		sql="INSERT INTO airticket.salelog (SALEID, NETID, OPERATORTYPE) \r\n" + 
				"VALUES ( ?, ?, '2')";
		jdbcOperator.queryForInt(sql,saleRecord.getSaleid() ,saleRecord.getNetid());
		
		 saleRecordDAO.saleTicket(saleRecord);
//		 座位数问题再说了，salerecord数据库其实少一个storeid，否则就很好弄了。这样稍微有一点点麻烦，有心情再测试了。上面一个方法里已经写好了，还未测试。故不添加。
//		 sql=" SELECT max(SALEID) FROM airticket.salerecord;";
//		 int saleid=(Integer) jdbcOperator.queryOneValue(sql);
//		 ticketStoreDAO.storenumDown(saleid-1);
		
		return "改签成功";
	}

	@Override
	public PageModel<SaleTotal> queryMonthSale(int netid, String month,
			String flightno, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		JdbcOperator jdbcOperator= new JdbcOperatorImpl(DataSourceConfig.getDataSource());
		
		List<SaleRecord> saleRecords=null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dt = null;
        String addMonth=month;
		try {
			dt = sdf.parse(month);
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(dt);  
	        rightNow.add(Calendar.MONTH, 1);  
	        Date dt1 = rightNow.getTime();  
	        addMonth = sdf.format(dt1); 
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "请输入正确的日期格式：yyyy-MM-dd"+e.getMessage());
		}  
		String sql="SELECT * FROM airticket.salerecord where netid=? and flightid=? and saletime>? and saletime<? ;";
		saleRecords=jdbcOperator.queryForJavaBeanList(sql, SaleRecord.class,netid, flightno,month,addMonth);
		String airname=(String) jdbcOperator.queryOneValue("SELECT DICNAME as airname FROM airticket.dirctory where dicid in (select dicid from airticket.flight where flightid=?);", flightno);
		
		SaleTotal flightSaleTotal=new SaleTotal();
		flightSaleTotal.setFlightno(flightno);
		flightSaleTotal.setAirline(airname);
		flightSaleTotal.setMonth(month+"至"+addMonth);
		for (SaleRecord saleRecord : saleRecords) {
			flightSaleTotal.setTicketmoney(flightSaleTotal.getTicketmoney()+saleRecord.getTicketmoney());
			flightSaleTotal.setTicketnum(flightSaleTotal.getTicketnum()+1);
			if (saleRecord.getSalestate().equals("1")) {
				flightSaleTotal.setTurnmoney(flightSaleTotal.getTurnmoney()+saleRecord.getTicketmoney());
				flightSaleTotal.setTurnnum(flightSaleTotal.getTurnnum()+1);
			}
		}
		flightSaleTotal.setTotalmoney(flightSaleTotal.getTicketmoney()-flightSaleTotal.getTurnmoney());
		
		List<SaleTotal> result=new ArrayList<SaleTotal>();
		result.add(flightSaleTotal);
		PageModel<SaleTotal> pageModel=new PageModel<SaleTotal>();
		pageModel.setResult(result);
		return pageModel;
	}

	@Override
	public Oiltax getOilTax() throws RemoteException {
		// TODO Auto-generated method stub
		String sql="select * from airticket.oiltax;";
		JdbcOperator jdbcOperator= new JdbcOperatorImpl(DataSourceConfig.getDataSource());
		
		return (Oiltax) jdbcOperator.queryForJavaBean(sql, Oiltax.class);
	}

	@Override
	public SaleRecord getSaleRecord(int netid, String fromCity, String toCity,
			String idcard, java.sql.Date planDate) throws RemoteException {
		// TODO Auto-generated method stub
		if (fromCity==null||fromCity==""||fromCity.length()==0) {
			fromCity="%";
		}
		if (toCity==null||toCity==""||toCity.length()==0) {
			toCity="%";
		}
		SaleRecord saleRecord= saleRecordDAO.getSaleRecord(netid,fromCity,toCity,idcard,planDate);
		return saleRecord;
	}

	@Override
	public SaleRecord queryRecordForTicket(String idCard)
			throws RemoteException {
		// TODO Auto-generated method stub
		SaleRecord saleRecord=saleRecordDAO.queryRecordForTicket(idCard);
		if (saleRecord!=null) {
			return saleRecord;
		}else {
			return null;
		}
		
	}

}
