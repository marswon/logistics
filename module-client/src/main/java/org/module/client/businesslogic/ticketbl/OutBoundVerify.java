package org.module.client.businesslogic.ticketbl;

import java.rmi.RemoteException;

import org.module.client.businesslogic.managementbl.WarehouseAdjust;
import org.module.client.businesslogicservice.management.TicketAndorderVerify;
import org.module.client.javaRMI.RmiClient;
import org.module.client.vo.OutBoundListVO;
import org.module.common.dataservice.MyList;
import org.module.common.dataservice.ticketdataservice.OutBoundListService;
import org.module.common.po.OutBoundListPO;
import org.module.common.po.State;

public class OutBoundVerify  implements TicketAndorderVerify{
	
	private MyList<OutBoundListVO> List;
	private OutBoundListService outBoundListDataGetter = new RmiClient().get(OutBoundListService.class);
	private WarehouseAdjust warehouse = new WarehouseAdjust();
	
	public MyList<OutBoundListVO> getAll(){
		this.List = new MyList<OutBoundListVO>();
		try {
			MyList<OutBoundListPO> pos = this.outBoundListDataGetter.getByState(State.SUBMITTED);
			for (OutBoundListPO outBoundListPO : pos) {
				this.List.add(new OutBoundListVO(outBoundListPO));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return this.List;
	}
	
	public boolean pass(int[] indexes){
		try{
			for(int i = indexes.length-1; i>=0; i--){
				OutBoundListVO vo = this.List.remove(indexes[i]);
				vo.setState(State.PASS);
				this.outBoundListDataGetter.update( vo.toPO() );
				this.warehouse.outBound(vo);
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean unpass(int[] indexes){
		try{
			for(int i = indexes.length-1; i>=0; i--){
				OutBoundListVO vo = this.List.remove(indexes[i]);
				vo.setState(State.UNPASS);
				this.outBoundListDataGetter.update( vo.toPO() );
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return true;
	}
}
