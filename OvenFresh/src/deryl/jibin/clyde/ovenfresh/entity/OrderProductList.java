package deryl.jibin.clyde.ovenfresh.entity;

import java.util.List;


public class OrderProductList {
	
	private List<OrderProduct> order ;
	private static OrderProductList instance;
	

	public List<OrderProduct> getOrderList() {
		return order;
	}	

	public void setOrderList(List<OrderProduct> storeList) {
		this.order = storeList;
	}
	
	public static OrderProductList getInstance()
	{
		if(instance == null){
			instance = new OrderProductList();
		}
		return instance;
	}
	
	public static void setInstance()
	{
		instance = null;
	}
}
