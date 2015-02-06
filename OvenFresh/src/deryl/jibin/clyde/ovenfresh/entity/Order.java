package deryl.jibin.clyde.ovenfresh.entity;

public class Order {
	
	private String orderId;
	private String orderDeliveryDate;
	private double price;
	private String status;
	private String orderGeneratedTime;
	private String deliveryTime;
	
	public Order()
	{}
	
	public Order(String orderId, String orderDeliveryDate, double price,
			String status, String deliveryTime, String orderGeneratedTime) {
		super();
		this.orderId = orderId;
		this.orderDeliveryDate = orderDeliveryDate;
		this.price = price;
		this.status = status;
		this.deliveryTime = deliveryTime;
		this.orderGeneratedTime = orderGeneratedTime;
	}
	
	

	public String getOrderGeneratedTime() {
		return orderGeneratedTime;
	}

	public void setOrderGeneratedTime(String orderGeneratedTime) {
		this.orderGeneratedTime = orderGeneratedTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDeliveryDate() {
		return orderDeliveryDate;
	}

	public void setOrderDeliveryDate(String orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	

}
