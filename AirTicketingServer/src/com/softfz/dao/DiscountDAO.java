package com.softfz.dao;

import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import com.softfz.config.DataSourceConfig;
import com.softfz.jdbc.JdbcOperator;
import com.softfz.jdbc.JdbcOperatorImpl;
import com.softfz.model.Discount;

public class DiscountDAO {
	private JdbcOperator jdbcOperator;

	public DiscountDAO() {
		DataSource dataSource = DataSourceConfig.getDataSource();
		jdbcOperator = new JdbcOperatorImpl(dataSource);
	}

	public void addDiscount(Discount discount) throws Exception {
		// TODO 自动生成的方法存根
		String sql="INSERT INTO airticket.discount(FLIGHTID,DISCOUNT,DISCOUNTDATETIME) VALUES (?,?,?); ";
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(discount.getDiscountdate());
		int result= jdbcOperator.queryForInt(sql, discount.getFlightid(),discount.getDiscount(),dateStr);
		if (result<=0) {
			throw new Exception("添加折扣失败");
		}
	}
}
