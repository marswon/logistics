package org.module.common.dataservice.managementdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.module.common.dataservice.MyList;
import org.module.common.po.DriverPO;


public interface DriversDataService  extends Remote{
	/**
	 * 前置：无
	 * 后置：返回所有司机PO
	 * @param s
	 * @return
	 */
	public MyList<DriverPO> getAll()throws RemoteException;
	/**
	 * 前置：数据库中不存在相同ID值的PO
	 * 后置：增加一个PO
	 * @param cp
	 * @return
	 */
	public boolean add(DriverPO cp)throws RemoteException;
	/**
	 * 前置：数据库中已经存在该PO
	 * 后置：删除一个PO
	 * @param cp
	 * @return
	 */
	public boolean delete(String id)throws RemoteException;
	/**
	 * 前置：数据库中存在这些PO
	 * 后置：删除这些PO
	 * @return
	 */
	public boolean delete(MyList<String> al)throws RemoteException;
	/**
	 * 前置：数据库中存在原PO
	 * 后置：修改该PO
	 * @param
	 * @return
	 */
	public boolean update(DriverPO newone)throws RemoteException;
}

