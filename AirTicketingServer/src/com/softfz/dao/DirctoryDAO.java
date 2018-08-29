package com.softfz.dao;

import java.util.List;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.Dirctory;

public class DirctoryDAO {
	private JdbcOperator jdbcOperator;
	
	public DirctoryDAO() {
		// TODO 自动生成的构造函数存根
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}

	public List<Dirctory> getAllAirPorts() {
		String sql="SELECT * FROM airticket.dirctory where fatherid='1';";
		return jdbcOperator.queryForJavaBeanList(sql, Dirctory.class);
	}

	public List<Dirctory> getAllAirLines() {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM airticket.dirctory where fatherid='2';";
		return jdbcOperator.queryForJavaBeanList(sql, Dirctory.class);
	}
	
	
}
