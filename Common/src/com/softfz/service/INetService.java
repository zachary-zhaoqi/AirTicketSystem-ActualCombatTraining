package com.softfz.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import com.softfz.model.BounceRecord;
import com.softfz.model.Flight;
import com.softfz.model.NetDealer;
import com.softfz.model.Oiltax;
import com.softfz.model.PageModel;
import com.softfz.model.SaleRecord;
import com.softfz.model.SaleTotal;
import com.softfz.model.SalesTicketInfoShow;

/**
 * 该接口定义了销售网点客户端的业务逻辑操作
 * @author Administrator
 *
 */
public interface INetService extends Remote {
	/**
	 * 传递消息给服务器端的信息显示框
	 * @param str
	 * @throws RemoteException 
	 */
	public void showMsgToServer(String str) throws RemoteException;
	/**
	 * 正常登录：返回销售网点对象帐号或者密码错误，抛出RemoteException异常
	 * @param netcode
	 * @param password
	 * @return
	 */
	public NetDealer login(String netcode, String password) throws RemoteException;
	
	/**
	 * 检查销售网点是否被冻结（被冻结就抛出被冻结的异常）
	 * @param netcode
	 * @return
	 * @throws RemoteException
	 */
	public void checkIsLock(String netcode) throws RemoteException;
	/**
	 * 修改销售网点的登陆密码
	 * @param netid
	 * @param passwordOld
	 * @param passwordNew
	 * @return 
	 * @throws RemoteException
	 */
	public Boolean updateNetClientPwd(int netid, String passwordOld, String passwordNew) throws RemoteException;	
	
	/**
	 * 航班查询，客户在订票之前可通过航班查询查找航班信息
	 * @param fromCity
	 * @param toCity
	 * @param planDate
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<Flight> queryFlights(String fromCity,String toCity,
			Date planDate, int currentPage, int pageSize) throws RemoteException;
	/**
	 * 通过航班查询之后客户选定指定的航班进行订票时调用
	 * @param flightid
	 * @return
	 */
	public Flight queryFlights(int flightid) throws RemoteException;
	
	/**
	 * 保存客户的订票信息
	 * @param record
	 * @return
	 */
	public String saleTicket(SaleRecord record) throws RemoteException;
	
	/**
	 * 保存客户的退票记录
	 * @param record
	 * @return
	 */
	public String cancelTicket(BounceRecord record) throws RemoteException;
	
	/**
	 * 保存客户的转签记录，注意：多了不退，少了需要补交
	 * @param recordid
	 * @param newPlanDate
	 * @return
	 */
	public String changeTicketDate(int recordid, Date newPlanDate) throws RemoteException;
	
	/**
	 * 销售网点月销售记录查询
	 * @param month
	 * @param flightno
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<SaleTotal> queryMonthSale(int netid, String month, String flightno,
			int currentPage, int pageSize) throws RemoteException;
	
	/**
	 * 查询燃油税信息
	 * @return
	 */
	public Oiltax getOilTax() throws RemoteException;
	
	/**
	 * 查询旅客的订票记录为退票和转签使用
	 * @param fromCity
	 * @param toCity
	 * @param idcard
	 * @param planDate
	 * @return
	 */
	public SaleRecord getSaleRecord(int netid, String fromCity, String toCity,
			String idcard, java.sql.Date planDate) throws RemoteException;
	
	/**
	 * 根据“身份证号”和“票的状态”，“起飞时间”查找符合条件的销售记录
	 * @param idCard
	 * @return
	 */
	public SaleRecord queryRecordForTicket(String idCard) throws RemoteException;
	
}
