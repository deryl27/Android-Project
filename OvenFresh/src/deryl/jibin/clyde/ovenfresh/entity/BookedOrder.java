package deryl.jibin.clyde.ovenfresh.entity;

import java.util.List;

public class BookedOrder {
	
	private Order orderDetails;
	private List<OrderProduct> orderItem;
	
	public BookedOrder()
	{}

	public BookedOrder(Order orderDetails, List<OrderProduct> orderItem) {
		super();
		this.orderDetails = orderDetails;
		this.orderItem = orderItem;
	}

	public Order getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Order orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<OrderProduct> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderProduct> orderItem) {
		this.orderItem = orderItem;
	}
	
	

}
