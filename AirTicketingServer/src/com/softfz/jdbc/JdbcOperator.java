package com.softfz.jdbc;

import java.util.List;

import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;

public interface JdbcOperator {
	
	/**
	 * 查询SQL语句返回一个整型的结果
	 * @param sql 查询语句
	 * @param params 语句中对应的参数
	 * @return 查询结果
	 */
	public int queryForInt(String sql, Object...params);
	
	/**
	 * 查询SQL语句返回一个长整型的结果
	 * @param sql 查询语句
	 * @param params 语句中对应的参数
	 * @return
	 */
	public long queryForLong(String sql, Object...params);
	
	public Object queryForJavaBean(String sql, Class javaBeanClass, Object...params);
	
	/**
	 * insert、delete、update处理
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object...params);
	
	/**
	 * insert、delete、update处理
	 * @param sql
	 * @param list
	 * @return
	 */
	public int update(String sql, List list);
	
	public List queryForJavaBeanList(String sql, Class javaBeanClass, Object...params);
	
	public PageModel queryPageModel(int currentPage, int pageSize, StringBuilder querySql,
			StringBuilder countSql, StringBuilder whereSql, List paramList,
			Class javaBeanType);
	
	//返回唯一值
	public Object queryOneValue(String sql,Object... params);	
}
