package com.softfz.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.softfz.dao.NetDealerDAO;
import com.softfz.dao.SystemUserDAO;
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

public class SystemServiceImpl extends UnicastRemoteObject implements ISystemService {
	private SystemUserDAO systemUserDAO;
	
	public SystemServiceImpl() throws RemoteException {
		systemUserDAO = new SystemUserDAO();
	}
	
	@Override
	public void showMsgToServer(String str) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemUser login(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		SystemUser systemUser=null;
		systemUser=systemUserDAO.getSystemUserByUsername(username,password);
		return systemUser;
		
	}

	@Override
	public Boolean updatePassword(String username, String oldpassword,
			String password) throws RemoteException {
		// TODO Auto-generated method stub
		
		return systemUserDAO.updateSystemUser(username, oldpassword, password);
	}

	@Override
	public boolean addSystemUser(String username, String password, String email,
			String realname, String telephone) throws RemoteException {
		// TODO Auto-generated method stub
		int result=systemUserDAO.addSystemUser(username, password, email, realname, telephone);
		if (result>0) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void modifySystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageModel<SystemUser> queryAllSystemUser(String username,
			int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPassword(List<Integer> userids) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkIsLock(String netcode) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockSystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockSystemuser(List<Integer> userids) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockSystemUser(SystemUser systemUser) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemUser querySystemUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<NetDealer> queryAllDealer(String netcode, String netname,
			int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNetDealer(int userid, String netcode, String netname,
			String password, String director, String telephone, String state,
			String address) throws RemoteException {
		// TODO Auto-generated method stub
		NetDealerDAO netDealerDAO=new NetDealerDAO();
		int result=netDealerDAO.addNetDealer(userid, netcode, netname, password, director, telephone, state, address);
		if (result>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void resetNetPassword(int netid, String passwordOld)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockNet(int netid) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockNet(int netid) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addFlight(Flight flight) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageModel<Flight> queryFlights(String flightno, String fromCity,
			String toCity, int type, int isstop, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDiscount(Discount discount) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOilTax(Oiltax oiltax) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oiltax getOilTax() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dirctory> getAllAirPorts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dirctory> getAllAirLines() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<NetSaleTotal> queryNetSaleTotal(String netcode,
			String month, int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<FlightSaleTotal> queryFlightSaleTotal(String flightno,
			String month, int currentPage, int pageSize) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockFlight(int flightId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDicID(String dicName) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addFlightStop(FlightStop flightStop) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Flight queryFlight(String flightno) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight queryFlight(int flightid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Discount queryDiscount(int flightid, String DiscountDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		try {
			SystemServiceImpl serviceImpl=new SystemServiceImpl();
			serviceImpl.login("zhaoda", "888888");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
}
