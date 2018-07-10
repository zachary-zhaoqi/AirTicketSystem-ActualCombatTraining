package com.softfz.dao;

import javax.sql.DataSource;

import org.omg.CosNaming.NameComponent;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;

public class NetDealerDAO {
	private JdbcOperator jdbcOperator;

	public NetDealerDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}
	
	/**
	 * 新增管理端用户
	 * @param netid
	 * @param passwordOld
	 * @param passwordNew
	 */
	public int addNetDealer(int userid, String netcode, String netname,
			String password, String director, String telephone, String state,
			String address){
		//取序列
		//构造插入语句
		//封装参数到List中
		//调用JDBC更新之
		//jdbcOperator.update(sql,parameterList);
		//没按上面的做
		String sql="INSERT INTO airticket.netdealer(userid,netcode,netname,password,director,telphone,state,address) VALUES (?,?,?,?,?,?,?,?);";
		int result=jdbcOperator.queryForInt(sql, userid,netcode,netname,password,director,telephone,state,address);
		return result;
	}
	
}
