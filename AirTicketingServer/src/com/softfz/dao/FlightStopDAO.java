package com.softfz.dao;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.FlightStop;

public class FlightStopDAO {
	private JdbcOperator jdbcOperator;

	public FlightStopDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}

	public int addFlightStop(FlightStop flightStop) {
		// TODO 自动生成的方法存根
		String sql="INSERT INTO airticket.flightstop " + 
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		int result=jdbcOperator.queryForInt(sql, Integer.toString(flightStop.getFlightid()),flightStop.getStopcity(),flightStop.getStopairport(),
				flightStop.getArrviedtime(),flightStop.getAgaintime(),Integer.toString(flightStop.getStopprice()),Integer.toString(flightStop.getFlightprice()),
				Integer.toString(flightStop.getAirrange()));
		
		return result;
	}
}
