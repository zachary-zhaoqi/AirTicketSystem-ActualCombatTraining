package com.softfz.dao;

import java.math.BigDecimal;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;

public class TicketStoreDAO {
	private JdbcOperator jdbcOperator;

	public TicketStoreDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}

	public int storenumDown(int storeid) {
		// TODO 自动生成的方法存根
		String sql="select storenum from airticket.ticketstore WHERE STOREID = ?;";
		BigDecimal storenum1=(BigDecimal) jdbcOperator.queryOneValue(sql, storeid);
		int storenum=storenum1.intValue();
		sql="UPDATE airticket.ticketstore SET STORENUM = ? WHERE STOREID = ?;";
		int result=jdbcOperator.queryForInt(sql, storenum-1,storeid);
		return result;
	}
	
	public int storenumUp(int storeid) {
		// TODO 自动生成的方法存根
		String sql="select storenum from airticket.ticketstore WHERE STOREID = ?;";
		BigDecimal storenum1=(BigDecimal) jdbcOperator.queryOneValue(sql, storeid);
		int storenum=storenum1.intValue();
		sql="UPDATE airticket.ticketstore SET STORENUM = ? WHERE STOREID = ?;";
		int result=jdbcOperator.queryForInt(sql, storenum+1,storeid);
		return result;
	}
	
}
