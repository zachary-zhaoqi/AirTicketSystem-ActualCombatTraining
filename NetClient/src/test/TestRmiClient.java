package test;

import java.rmi.RemoteException;

import com.softfz.RMIFactory;
import com.softfz.model.NetDealer;
import com.softfz.service.INetService;

public class TestRmiClient {
	public static void main(String[] args) {
		INetService netService=RMIFactory.getService();
		try {
			NetDealer dealer=  netService.login("A0000001", "888888");
			if(dealer!=null){
				System.out.println("µÇÂ½³É¹¦£¡");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
