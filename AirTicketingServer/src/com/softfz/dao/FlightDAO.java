package com.softfz.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.Flight;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;

public class FlightDAO {
	private JdbcOperator jdbcOperator;

	public FlightDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}
//	public static void main(String[] args) {
//		FlightDAO flightDAO=new FlightDAO();
//		flightDAO.name();
//		
//	}
//	public void name() {
//		String sql=" SELECT max(FLIGHTID) FROM airticket.flight;";
//		int flightid=(Integer) jdbcOperator.queryOneValue(sql);
//		System.out.println(flightid);
//	}

	public int addFlight(Flight flight) {
		String sql="INSERT INTO airticket.flight(userid,dicid,flightno,startairport,endairport,TYPE,PLANSTARTTIME," + 
				"PLANENDTIME,AIRRANGE,PRICE,FROMCITY,TOCITY,FLIGHTTYPE,TICKETNUM,ISSTOP) " + 
				"VALUES (?, ?, ?, ?," + 
				" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcOperator.queryForInt(sql, flight.getUserid(),flight.getDicid(),flight.getFlightno(),flight.getStartairport(),flight.getEndairport(),flight.getType(),flight.getPlanstarttime(),
				flight.getPlanendtime(),flight.getAirrange(),flight.getPrice(),flight.getFromcity(),flight.getTocity(),flight.getFlighttype(),flight.getTicketnum(),flight.getIsstop());
		
		sql=" SELECT max(FLIGHTID) FROM airticket.flight;";
		
		
		int flightid=(Integer) jdbcOperator.queryOneValue(sql);
		
		return flightid;
	}

	public  PageModel<Flight> queryFlights(String flightno, String fromCity, String toCity, int type, int isstop,
			int currentPage, int pageSize) {
		// TODO 自动生成的方法存根
		flightno=nullstringToCuringa(flightno);
		fromCity=nullstringToCuringa(fromCity);
		toCity=nullstringToCuringa(toCity);
		
		String sql;
		List<Flight> flights;
		sql="SELECT * FROM airticket.flight where flightno like ? and FROMCITY like ? and TOCITY like ? and TYPE like ? and ISSTOP like ? ;";
		flights=jdbcOperator.queryForJavaBeanList(sql, Flight.class, flightno,fromCity,toCity,type,isstop);
		
		PageModel<Flight> pageModel=new PageModel<Flight>()	;
		pageModel.setAllCount(flights.size());
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(pageSize);
		List<Flight> pageFlights=new ArrayList<Flight>();
		for(int i=1+(currentPage-1)*pageSize;i<=pageSize+(currentPage-1)*pageSize;i++){
			if (flights.size()<i) {
				break;
			}
			Flight flight=flights.get(i-1);
			pageFlights.add(flight);
		}
		pageModel.setResult(pageFlights);
		return pageModel;
	}

	/**
	 * @param string
	 * 
	 * 辅助方法
	 */
	private  String nullstringToCuringa(String string) {
		if (string==""||string.length()==0||string==null) {
			return "%";
		}else {
			return string;
		}
	}

	public PageModel<Flight> queryFlights(String fromCity, String toCity, Date planDate, int currentPage,
			int pageSize) {
		// TODO 自动生成的方法存根
		String sql;
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(planDate);
		List<Flight> flights;
		PageModel<Flight> pageModel=new PageModel<Flight>()	;
		
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(pageSize);
		List<Flight> pageFlights=new ArrayList<Flight>();
		
		
		List<Flight> flightids;
		sql="select flightid from airticket.flight where FROMCITY=? and TOCITY=?";
		flightids=jdbcOperator.queryForJavaBeanList(sql, Flight.class,fromCity,toCity);
		
		for (Flight flightid : flightids) {
			sql="select aaaa.*,b.storenum,b.discount,b.STOREID\r\n" + 
					"		from airticket.flight aaaa left join \r\n" + 
					"										(\r\n" + 
					"											select aa.*, bb.discount \r\n" + 
					"											from airticket.ticketstore aa left join airticket.discount bb\r\n" + 
					"											on aa.flightid=bb.flightid and aa.TICKETDATETIME=bb.DISCOUNTDATETIME\r\n" + 
					"										) b \r\n" + 
					"	on aaaa.flightid=b.flightid \r\n" + 
					"	where aaaa.flightid =?\r\n" + 
					"	and b.TICKETDATETIME=?";
			flights=jdbcOperator.queryForJavaBeanList(sql, Flight.class,flightid.getFlightid(),dateStr);
			
			pageModel.setAllCount(pageModel.getAllCount()+flights.size());
			for(int i=1+(currentPage-1)*pageSize;i<=pageSize+(currentPage-1)*pageSize;i++){
				if (flights.size()<i) {
					break;
				}
				Flight flight=flights.get(i-1);
				if (flight.getDiscount()==0.0) {
					flight.setDiscount(1.0);	
				}
				pageFlights.add(flight);
			}
			
		}
		pageModel.setResult(pageFlights);
		return pageModel;
	}

	public Flight queryFlights(int flightid) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM airticket.flight where flightid=?;";
		Flight flight=(Flight) jdbcOperator.queryForJavaBean(sql, Flight.class, flightid);
		return flight;
	}

	
}
