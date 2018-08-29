package com.softfz.dao;

import java.sql.Date;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.SaleRecord;

public class SaleRecordDAO {
	private JdbcOperator jdbcOperator;

	public SaleRecordDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}

	public SaleRecord queryRecordForTicket(String idCard) {
		// TODO 自动生成的方法存根
		String sql="select * from airticket.salerecord where idcard=? and salestate='0' and ARRTIME>current_timestamp;";
		return (SaleRecord) jdbcOperator.queryForJavaBean(sql, SaleRecord.class, idCard);
	}

	public int saleTicket(SaleRecord record) {
		// TODO 自动生成的方法存根
		String sql=" SELECT max(SALEID) FROM airticket.salerecord;";
		int saleid=(Integer) jdbcOperator.queryOneValue(sql);
		record.setSaleid(saleid+1);
		
		sql="INSERT INTO airticket.salerecord (SALEID,NETID, FLIGHTID, TICKETMONEY, "
				+ "AIRPORTTAX, OILTAX, CUSTNAME, CUSTTEL, IDCARD, STARTAIRPORT, "
				+ "ENDAIRPOTR, ARRTIME, STARTTIME, SALESTATE, FROMCITY, TOCITY) \r\n" + 
				"VALUES (?,?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', ?, ?)";
		int result=jdbcOperator.queryForInt(sql, record.getSaleid(),record.getNetid(),record.getFlightid(),record.getTicketmoney(),
				record.getAirporttax(),record.getOiltax(),record.getCustname(),record.getCusttel(),record.getIdcard(),record.getStartairport(),
				record.getEndairpotr(),record.getArrtime(),record.getStarttime(),record.getFromcity(),record.getTocity());
		if (result>0) {
			sql="INSERT INTO airticket.salelog (SALEID, NETID, OPERATORTYPE) \r\n" + 
					"VALUES ( ?, ?, '0')";
			result=jdbcOperator.queryForInt(sql, record.getSaleid(),record.getNetid());
		}
		return result;
	}
	public static void main(String[] args) {
		SaleRecordDAO saleRecordDAO=new SaleRecordDAO();
		saleRecordDAO.saleTicket(new SaleRecord());
		
	}

	public SaleRecord getSaleRecord(int netid, String fromCity, String toCity, String idcard, Date planDate) {
		// TODO 自动生成的方法存根
		String sql="select * from airticket.salerecord where idcard=? and salestate='0'and STARTTIME>current_timestamp;";
		SaleRecord saleRecord=(SaleRecord) jdbcOperator.queryForJavaBean(sql, SaleRecord.class, idcard);
		return saleRecord;
	}
}
