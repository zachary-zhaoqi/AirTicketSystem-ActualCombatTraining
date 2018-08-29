package com.softfz.service;

import java.nio.channels.NonWritableChannelException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import com.softfz.config.DataSourceConfig;
import com.softfz.dao.DirctoryDAO;
import com.softfz.dao.DiscountDAO;
import com.softfz.dao.FlightDAO;
import com.softfz.dao.FlightStopDAO;
import com.softfz.dao.NetDealerDAO;
import com.softfz.dao.SystemUserDAO;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.Dirctory;
import com.softfz.model.Discount;
import com.softfz.model.Flight;
import com.softfz.model.FlightSaleTotal;
import com.softfz.model.FlightStop;
import com.softfz.model.NetDealer;
import com.softfz.model.NetSaleTotal;
import com.softfz.model.Oiltax;
import com.softfz.model.PageModel;
import com.softfz.model.SaleRecord;
import com.softfz.model.SystemUser;

public class SystemServiceImpl extends UnicastRemoteObject implements ISystemService {
	private SystemUserDAO systemUserDAO;
	private NetDealerDAO netDealerDAO;
	private FlightDAO flightDAO;
	private DiscountDAO discountDAO;
	
	public SystemServiceImpl() throws RemoteException {
		systemUserDAO = new SystemUserDAO();
		netDealerDAO=new NetDealerDAO();
		flightDAO=new FlightDAO();
		discountDAO=new DiscountDAO();
	}
	
	@Override
	public void showMsgToServer(String str) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemUser login(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		SystemUser systemUser=null;
		systemUser=systemUserDAO.getSystemUserByUsername(username,password);
		return systemUser;
		
	}

	@Override
	public Boolean updatePassword(String username, String oldpassword,
			String password) throws RemoteException {
		// TODO Auto-generated method stub
		return systemUserDAO.updateSystemUser(username, oldpassword, password);
	}

	@Override
	public boolean addSystemUser(String username, String password, String email,
			String realname, String telephone) throws RemoteException {
		// TODO Auto-generated method stub
		int result=systemUserDAO.addSystemUser(username, password, email, realname, telephone);
		if (result>0) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean modifySystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
		return systemUserDAO.modifySystemUserByJavaBean(systemUser);
	}

	@Override
	public PageModel<SystemUser> queryAllSystemUser(String username,
			int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		
		PageModel<SystemUser> pageModel=systemUserDAO.querySystemUser(username, currentPage, pageSize);
		return pageModel;
	}

	@Override
	public void resetPassword(List<Integer> userids) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkIsLock(String netcode) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockSystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockSystemuser(List<Integer> userids) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockSystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemUser querySystemUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return systemUserDAO.getSystemUserByUsername(username);
	}

	@Override
	public PageModel<NetDealer> queryAllDealer(String netcode, String netname,
			int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		PageModel<NetDealer> pageModel=netDealerDAO.querNetDealer(netcode,netname,currentPage,pageSize);
		return pageModel;
	}

	@Override
	public boolean addNetDealer(int userid, String netcode, String netname,
			String password, String director, String telephone, String state,
			String address) throws RemoteException {
		// TODO Auto-generated method stub
		NetDealerDAO netDealerDAO=new NetDealerDAO();
		int result=netDealerDAO.addNetDealer(userid, netcode, netname, password, director, telephone, state, address);
		if (result>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void resetNetPassword(int netid, String passwordOld)
			throws Exception {
		// TODO Auto-generated method stub
		NetDealerDAO netDealerDAO=new NetDealerDAO();
		netDealerDAO.resetNetPassword(netid,passwordOld,"123456");
		
	}

	@Override
	public void lockNet(int netid) throws RemoteException {
		// TODO Auto-generated method stub
		netDealerDAO.lockNet(netid);
		
	}

	@Override
	public void unlockNet(int netid) throws RemoteException {
		// TODO Auto-generated method stub
		netDealerDAO.unlockNet(netid);
	}

	@Override
	public int addFlight(Flight flight) throws RemoteException {
		// TODO Auto-generated method stub
		FlightDAO flightDAO=new FlightDAO();
		
		return flightDAO.addFlight(flight);
	}

	@Override
	public PageModel<Flight> queryFlights(String flightno, String fromCity,
			String toCity, int type, int isstop, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		 PageModel<Flight> pageModel=flightDAO.queryFlights(flightno,fromCity,toCity,type,isstop,currentPage,pageSize);
		return pageModel;
	}

	@Override
	public void addDiscount(Discount discount) throws RemoteException,Exception {
		// TODO Auto-generated method stub
		
		discountDAO.addDiscount(discount);
		
	}

	@Override
	public void setOilTax(Oiltax oiltax) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oiltax getOilTax() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dirctory> getAllAirPorts() throws RemoteException {
		// TODO Auto-generated method stub
		DirctoryDAO dirctoryDAO=new DirctoryDAO();
		return dirctoryDAO.getAllAirPorts();
	}

	@Override
	public List<Dirctory> getAllAirLines() throws RemoteException {
		// TODO Auto-generated method stub
		DirctoryDAO dirctoryDAO=new DirctoryDAO();
		return dirctoryDAO.getAllAirLines();	
	}

	@Override
	public PageModel<NetSaleTotal> queryNetSaleTotal(String netcode,
			String month, int currentPage, int pageSize) throws RemoteException {
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
		String sql="SELECT * FROM airticket.netdealer where netcode=?;";
		NetDealer netDealer =(NetDealer) jdbcOperator.queryForJavaBean(sql, NetDealer.class, netcode);
		
		sql="SELECT * FROM airticket.salerecord where netid=? and saletime>? and saletime<? ;";
		saleRecords=jdbcOperator.queryForJavaBeanList(sql, SaleRecord.class, netDealer.getNetid(),month,addMonth);
		
		NetSaleTotal netSaleTotal=new NetSaleTotal();
		netSaleTotal.setNetcode(netcode);
		netSaleTotal.setMonth(month);
		netSaleTotal.setNetname(netDealer.getNetname());
		int i=0;
		for (SaleRecord saleRecord : saleRecords) {
			i++;
			netSaleTotal.setTicketnum(i);
			netSaleTotal.setTicketmoney(netSaleTotal.getTicketmoney()+saleRecord.getTicketmoney());
			if (saleRecord.getSalestate().equals("1")) {
				netSaleTotal.setTurnnum(netSaleTotal.getTurnnum()+1);
				netSaleTotal.setTurnmoney(netSaleTotal.getTurnmoney()+saleRecord.getTicketmoney());
			}
		}
		netSaleTotal.setTotalmoney(netSaleTotal.getTicketmoney()-netSaleTotal.getTurnmoney());
		List<NetSaleTotal>result=new ArrayList<NetSaleTotal>();
		result.add(netSaleTotal);
		PageModel<NetSaleTotal> pageModel=new PageModel<NetSaleTotal>();
		pageModel.setResult(result);
		return pageModel;
	}

	@Override
	public PageModel<FlightSaleTotal> queryFlightSaleTotal(String flightno,
			String month, int currentPage, int pageSize) throws RemoteException {
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
		String sql="SELECT * FROM airticket.salerecord where flightid=? and saletime>? and saletime<? ;";
		saleRecords=jdbcOperator.queryForJavaBeanList(sql, SaleRecord.class, flightno,month,addMonth);
		String airname=(String) jdbcOperator.queryOneValue("SELECT DICNAME as airname FROM airticket.dirctory where dicid in (select dicid from airticket.flight where flightid=?);", flightno);
		
		FlightSaleTotal flightSaleTotal=new FlightSaleTotal();
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
		
		List<FlightSaleTotal> result=new ArrayList<FlightSaleTotal>();
		result.add(flightSaleTotal);
		PageModel<FlightSaleTotal> pageModel=new PageModel<FlightSaleTotal>();
		pageModel.setResult(result);
		return pageModel;
		
	}

	@Override
	public void lockFlight(int flightId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDicID(String dicName) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addFlightStop(FlightStop flightStop) throws RemoteException {
		// TODO Auto-generated method stub
		FlightStopDAO flightStopDAO=new FlightStopDAO();
		int result=flightStopDAO.addFlightStop(flightStop);
		if (result>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Flight queryFlight(String flightno) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight queryFlight(int flightid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Discount queryDiscount(int flightid, String DiscountDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockSystemUser(List<Integer> userids) throws RemoteException {
		// TODO 自动生成的方法存根
		
	}
	
	public static void main(String[] args) {
	try {
		SystemServiceImpl serviceImpl=new SystemServiceImpl();
		List<Dirctory> dirctories=serviceImpl.getAllAirPorts();
		for (Dirctory dirctory : dirctories) {
			System.out.println(dirctory.getDicname());
		}
	} catch (RemoteException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
	}
	
}
