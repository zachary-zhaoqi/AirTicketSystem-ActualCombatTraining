package com.softfz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.NetDealer;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;
import com.softfz.suport.ConnectionHandler;

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

	public void resetNetPassword(int netid, String passwordOld, String passwordNew) throws Exception {
		// TODO 自动生成的方法存根
		String sql="UPDATE airticket.netdealer SET password=? where netid=?;";
		int result=jdbcOperator.update(sql, passwordNew,netid);
		if (result>0) {
			return;
		} else {
			throw new Exception("更新失败");
		}
	}

	public PageModel<NetDealer> querNetDealer(String netcode, String netname, int currentPage, int pageSize) {
		// TODO 自动生成的方法存根
		String sql;
		List<NetDealer> allNetDealer;
		if (netcode==""||netcode==null||netcode.length()==0) {
			if (netname==""||netname==null||netname.length()==0) {
				sql="SELECT * FROM airticket.netdealer;";
				allNetDealer=jdbcOperator.queryForJavaBeanList(sql, NetDealer.class);
			}else {
				sql="SELECT * FROM airticket.netdealer where netname=?;";
				allNetDealer=jdbcOperator.queryForJavaBeanList(sql, NetDealer.class,netname);
			}
		}else {
			if (netname==""||netname==null||netname.length()==0) {
				sql="SELECT * FROM airticket.netdealer where netcode=?;";
				allNetDealer=jdbcOperator.queryForJavaBeanList(sql, NetDealer.class,netcode);
			}else {
				sql="SELECT * FROM airticket.netdealer where netcode=? and netname=?;";
				allNetDealer=jdbcOperator.queryForJavaBeanList(sql, NetDealer.class,netcode,netname);
			}
		}
		
		PageModel<NetDealer> pageModel=new PageModel<NetDealer>();
		pageModel.setAllCount(allNetDealer.size());
		pageModel.setCurrentPage(currentPage);
		pageModel.setPageSize(pageSize);
		List<NetDealer>netDealers=new ArrayList<NetDealer>();
		for(int i=1+(currentPage-1)*pageSize;i<=pageSize+(currentPage-1)*pageSize;i++){
			if (allNetDealer.size()<i) {
				break;
			}
			NetDealer netDealer=allNetDealer.get(i-1);
			netDealers.add(netDealer);
		}
		pageModel.setResult(netDealers);
		return pageModel;
	}

	public void lockNet(int netid) {
		// TODO 自动生成的方法存根
		String sql="UPDATE airticket.netdealer SET state=? where netid=?;";
		int result=jdbcOperator.update(sql,"1",netid);
	}

	public void unlockNet(int netid) {
		// TODO 自动生成的方法存根
		String sql="UPDATE airticket.netdealer SET state=? where netid=?;";
		int result=jdbcOperator.update(sql,"0",netid);
	}

	public NetDealer login(String netcode, String password) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM airticket.netdealer where NETCODE=? and PASSWORD=?;";
		NetDealer netDealer=(NetDealer) jdbcOperator.queryForJavaBean(sql, NetDealer.class, netcode,password);
		return netDealer;
	}

	public int updateNetClientPwd(int netid, String passwordOld, String passwordNew) {
		// TODO 自动生成的方法存根
		String sql="select netid from airticket.netdealer where netid=? and password=?;";
		
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		int netid1=0;
		try {
			conn = ConnectionHandler.getConnection(DataSourceConfig.getDataSource());
			//完成查询并返回查询结果的数据
			if (conn!=null) {
				preparedStatement=conn.prepareStatement(sql);
				
				preparedStatement.setInt(1, netid);
				preparedStatement.setString(2, passwordOld);
				resultSet=preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					netid1=resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally{
			if(conn != null){
				ConnectionHandler.closeConn(conn);
			}
			if (preparedStatement!=null) {
				ConnectionHandler.closeStatement(preparedStatement);
			}
			if (resultSet!=null) {
				ConnectionHandler.closeResultSet(resultSet);
			}
		}
		
		sql="UPDATE airticket.netdealer SET PASSWORD = ? WHERE netid = ?;";
		int result=jdbcOperator.queryForInt(sql, passwordNew,netid1);
		return result;
	}
	
}
