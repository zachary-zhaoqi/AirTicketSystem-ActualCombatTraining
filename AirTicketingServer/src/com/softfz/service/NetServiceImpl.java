package com.softfz.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import com.softfz.model.BounceRecord;
import com.softfz.model.Flight;
import com.softfz.model.NetDealer;
import com.softfz.model.Oiltax;
import com.softfz.model.PageModel;
import com.softfz.model.SaleRecord;
import com.softfz.model.SaleTotal;

public class NetServiceImpl extends UnicastRemoteObject implements INetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void showMsgToServer(String str) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public NetDealer login(String netcode, String password)
			throws RemoteException {
		if("A0000001".equals(netcode) && "888888".equals(password)){
			NetDealer nd=new NetDealer();
			nd.setNetid(1);
			nd.setNetcode("A0000001");
			nd.setNetname("华林售票点");
			nd.setState("0");
			return nd;
		}
		return null;
	}

	@Override
	public void checkIsLock(String netcode) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean updateNetClientPwd(int netid, String passwordOld,
			String passwordNew) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<Flight> queryFlights(String fromCity, String toCity,
			Date planDate, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight queryFlights(int flightid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saleTicket(SaleRecord record) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelTicket(BounceRecord record) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeTicketDate(int recordid, Date newPlanDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<SaleTotal> queryMonthSale(int netid, String month,
			String flightno, int currentPage, int pageSize)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Oiltax getOilTax() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleRecord getSaleRecord(int netid, String fromCity, String toCity,
			String idcard, java.sql.Date planDate) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleRecord queryRecordForTicket(String idCard)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
