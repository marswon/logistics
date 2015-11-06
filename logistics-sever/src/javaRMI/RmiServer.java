package javaRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import data.DataFactory;
import data.userdata.UserDataImpl;
import dataservice.DataFactotyService;
import dataservice.userdataservice.UserDataService;

public class RmiServer {

	UserDataService user;
	DataFactotyService dataFactory = new DataFactory();
	public RmiServer() {
		
	}
	public void user(){
		try {
			LocateRegistry.createRegistry(1099);
			user = (UserDataService) dataFactory.creatDataObject("user");
			Naming.rebind("rmi://127.0.0.1/user", user);
			System.out.println("user Server is ready.");    
		} catch (RemoteException e) {
			
			System.out.println("user Server failed: " + e);
		} catch (MalformedURLException e) {
			
			System.out.println("user Server failed: " + e);
		}
	}
	public static void main(String[] args){
		new RmiServer().user();
	}

}