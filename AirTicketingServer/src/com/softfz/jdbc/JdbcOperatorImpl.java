package com.softfz.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.softfz.config.DataSourceConfig;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;
import com.softfz.suport.ConnectionHandler;

public class JdbcOperatorImpl implements JdbcOperator{

	private DataSource dataSource;
	public JdbcOperatorImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public int queryForInt(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		int result=-1;
		try {
			conn = ConnectionHandler.getConnection(dataSource);
			//完成查询并返回查询结果的数据
			if (conn!=null) {
				preparedStatement=conn.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i+1, params[i]);
				}
				result=preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally{
			if(conn != null){
				ConnectionHandler.closeConn(conn);
				return result;
			}
		}
		return result;
	}
	@Override
	public long queryForLong(String sql, Object... params) {
		// TODO Auto-generated method stub
		
		return 0;
	}
	@Override
	public Object queryForJavaBean(String sql, Class javaBeanClass,
			Object... params) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Object object=null;
		try {
			conn = ConnectionHandler.getConnection(dataSource);
			//完成查询并返回查询结果的数据
			if (conn!=null) {
				preparedStatement=conn.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i+1, params[i]);
				}
				resultSet=preparedStatement.executeQuery();
				ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
				int count=resultSetMetaData.getColumnCount();
				
				while (resultSet.next()) {
					object=javaBeanClass.newInstance();
					Map map=new HashMap();
					for (int i = 1; i <= count; i++) {
						Object value=resultSet.getObject(i);
						String name =resultSetMetaData.getColumnName(i).toLowerCase();
						map.put(name, value);
					}
					BeanUtils.populate(object, map);
					
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
		return object;
	}
	@Override
	public int update(String sql, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(String sql, List list) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List queryForJavaBeanList(String sql, Class javaBeanClass,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageModel queryPageModel(int currentPage, int pageSize,
			StringBuilder querySql, StringBuilder countSql,
			StringBuilder whereSql, List paramList, Class javaBeanType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) {
//		String sql="SELECT * FROM airticket.systemuser where username=?";
//		String name="admin";
//		JdbcOperator jdbcOperator=new JdbcOperatorImpl(DataSourceConfig.getDataSource());
//		SystemUser systemUser=(SystemUser)jdbcOperator.queryForJavaBean(sql,SystemUser.class,name);
//		System.out.println(systemUser.toString());
		
		String sql="UPDATE airticket.systemuser SET password=? WHERE userid=?;";
		JdbcOperator jdbcOperator=new JdbcOperatorImpl(DataSourceConfig.getDataSource());
		int i=jdbcOperator.queryForInt(sql,"777777" ,"2");
		System.out.println(i);
	}
}
