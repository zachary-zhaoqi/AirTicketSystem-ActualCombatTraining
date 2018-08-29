package com.softfz.suport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.softfz.config.DataSourceConfig;

/**
 * 连接（事务）等辅助类
 * @author Administrator
 *
 */
public class ConnectionHandler {
	
	public static Connection getConnection(){
		try {
			return DataSourceConfig.getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					e.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
		return null;
	}
	
	public static Connection getConnection(DataSource dataSource){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					e.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
		return null;
	}
	
	public static void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					e.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
	}

	public static void closeStatement(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					e.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		// TODO Auto-generated method stub
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					e.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
	}
	
}
