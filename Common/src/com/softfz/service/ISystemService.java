package com.softfz.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.softfz.model.Dirctory;
import com.softfz.model.Discount;
import com.softfz.model.Flight;
import com.softfz.model.FlightSaleTotal;
import com.softfz.model.FlightStop;
import com.softfz.model.NetDealer;
import com.softfz.model.NetSaleTotal;
import com.softfz.model.Oiltax;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;
/**
 * 该接口定义了销售网点客户端的业务逻辑操作,该接口中设计的SystemUser等类均可从E-R图中生成相关的实体对象，NetSaleTotal和FlightSaleTotal为网点月销售统计以及航班月销售统计，这两个类均为根据两个统计查询需要显示的数据设计而成，可参考网点月销售统计中的SaleTotal。
 * @author Administrator
 *
 */
public interface ISystemService extends Remote {
	/**
	 * 传递消息给服务器端的信息显示框
	 * @param str
	 * @throws RemoteException 
	 */
	public void showMsgToServer(String str) throws RemoteException;	
	/**
	 * 正常登录：返回管理员实体对象帐号或者密码错误，抛出RemoteException异常
	 * @param username
	 * @param password
	 * @return
	 */
	public SystemUser login(String username, String password)throws RemoteException;
	
	
	/**
	 * 如果旧密码错误抛出密码错误RemoteException
	 * @param username
	 * @param oldpassword
	 * @param password
	 * @return 
	 */
	public Boolean updatePassword(String username, String oldpassword,
			String password) throws RemoteException;
	
	
	/**
	 * 如果帐号重名抛出重名RemoteException
	 * @param user
	 * @return 
	 */
	public boolean addSystemUser(String username, String password, String email, String realname, String telephone) throws RemoteException;
	
	/**
	 * 根据传入的SystemUser对象去更新表中的信息
	 * @param systemUser
	 * @return 
	 * @throws RemoteException
	 */
	public boolean modifySystemUser(SystemUser systemUser) throws RemoteException;
	
	
	/**
	 * 管理员列表分页查询
	 * @param username
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<SystemUser> queryAllSystemUser(String username,
			int currentPage, int pageSize) throws RemoteException;
	
	
	/**
	 * 重置管理员的密码为初始密码888888
	 * @param userids
	 */
	public void resetPassword(List<Integer> userids) throws RemoteException;
	
	
	/**
	 * 冻结管理员帐号
	 * @param userids
	 */
	public void lockSystemUser(List<Integer> userids) throws RemoteException;
	
	/**
	 * 检查管理员账号是否被冻结（被冻结就抛出被冻结的异常）
	 * @param netcode
	 * @return
	 * @throws RemoteException
	 */
	public void checkIsLock(String netcode) throws RemoteException;
	
	
	/**
	 * 通过传入JavaBean，冻结管理员账号
	 * @param systemUser
	 * @throws RemoteException
	 */
	public void lockSystemUser(SystemUser systemUser) throws RemoteException;
	
	
	/**
	 * 解除冻结的管理员帐号
	 * @param userids
	 */
	public void unlockSystemuser(List<Integer> userids) throws RemoteException;
	
	/**
	 * 通过传入JavaBean，解冻管理员账号
	 * @param systemUser
	 * @throws RemoteException
	 */
	public void unlockSystemUser(SystemUser systemUser) throws RemoteException;
	
	/**
	 * 根据用户名查找系统管理员信息
	 * @param username
	 * @return
	 * @throws RemoteException
	 */
	public SystemUser querySystemUser(String username) throws RemoteException;
	
	/**
	 * 销售网点查询
	 * @param netcode
	 * @param netname
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<NetDealer> queryAllDealer(String netcode,
			String netname, int currentPage, int pageSize) throws RemoteException;
	
	
	/**
	 * 添加销售网点信息
	 * @param dealer
	 * @return 
	 */
	public boolean addNetDealer(int userid, String netcode, String netname, String password,
			String director, String telephone, String state, String address) throws RemoteException;
	
	
	/**
	 * 重置销售网点的密码为123456
	 * @param netids
	 * @throws RemoteException,Exception 
	 */
	public void resetNetPassword(int netid, String passwordOld) throws RemoteException, RemoteException,Exception;
//	public void resetNetPassword(List<Integer> netids) throws RemoteException;
	
	
	
	/**
	 * 冻结销售网点
	 * @param netids
	 */
	public void lockNet(int netid) throws RemoteException;
//	public void lockNet(List<Integer> netids) throws RemoteException;
	
	
	/**
	 * 解除冻结的销售网点
	 * @param netids
	 */
	public void unlockNet(int netid) throws RemoteException;
//	public void unlockNet(List<Integer> netids) throws RemoteException;
	
	
	/**
	 * 添加航班信息
	 * @param flight
	 */
	public int addFlight(Flight flight) throws RemoteException;
	
	
	/**
	 * 查询航班信息
	 * @param flightno
	 * @param fromCity
	 * @param toCity
	 * @param type
	 * @param isstop
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<Flight> queryFlights(String flightno, String fromCity,
			String toCity, int type, int isstop, int currentPage, int pageSize)
			 throws RemoteException;
	
	
	/**
	 * 添加折扣信息
	 * @param discount
	 * @throws Exception 
	 */
	public void addDiscount(Discount discount) throws RemoteException, Exception;
	
	
	/**
	 * 设置燃油税
	 * @param oiltax
	 */
	public void setOilTax(Oiltax oiltax) throws RemoteException;
	
	/**
	 * 获取燃油税
	 * @return
	 * @throws RemoteException
	 */
	public Oiltax getOilTax() throws RemoteException;
	
	/**
	 * 查询机场信息
	 * @return
	 */
	public List<Dirctory> getAllAirPorts() throws RemoteException;
	
	
	/**
	 * 查询航空公司信息
	 * @return
	 */
	public List<Dirctory> getAllAirLines() throws RemoteException;
	
	
	/**
	 * 按照销售网点分类，以自然月汇总统计各个销售网点的销售情况
	 * @param netcode
	 * @param month
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<NetSaleTotal> queryNetSaleTotal(String netcode, String month,
			int currentPage, int pageSize) throws RemoteException;
	
	
	/**
	 * 航班销售统计：按航班分类，以自然月汇总统计所有销售网点的销售情况
	 * @param flightno
	 * @param month
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<FlightSaleTotal> queryFlightSaleTotal(String flightno,
			String month, int currentPage, int pageSize) throws RemoteException;
	
	public void lockFlight(int flightId) throws RemoteException;

	public int getDicID(String dicName) throws RemoteException;

	/**
	 * 新增航班经停信息
	 * @param flightStop
	 * @return 
	 * @throws RemoteException
	 */
	public boolean addFlightStop(FlightStop flightStop) throws RemoteException;
	
	/**
	 * 根据航班编号查询航班信息
	 * @param flightno
	 * @return
	 * @throws RemoteException
	 */
	public Flight queryFlight(String flightno) throws RemoteException;
	
	/**
	 * 根据航班ID查询航班信息
	 * @param flightno
	 * @return
	 * @throws RemoteException
	 */
	public Flight queryFlight(int flightid) throws RemoteException;
	
	/**
	 * 查询航班折扣
	 * @param flightid
	 * @return
	 * @throws RemoteException
	 */
	public Discount queryDiscount(int flightid, String DiscountDate) throws RemoteException;
	
	
}
