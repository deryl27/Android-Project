package deryl.jibin.clyde.ovenfresh.entity;

import java.util.List;

public class BookedOrderList {

	private static BookedOrderList instance;
	private List<BookedOrder> bookedOrderList ;
	private List<BookedOrder> completeOrderList;
	private List<BookedOrder> readyOrderList;
	private List<BookedOrder> cancelOrderList;
	
	public static BookedOrderList getInstance() {
		if(instance ==  null)
		{
			instance = new BookedOrderList();
		}
		return instance;
	}
	public static void setInstance(BookedOrderList instance) {
		instance = null;
	}
	public List<BookedOrder> getBookedOrderList() {
		return bookedOrderList;
	}
	public void setBookedOrderList(List<BookedOrder> bookedOrderList) {
		this.bookedOrderList = bookedOrderList;
	}
	public List<BookedOrder> getCompleteOrderList() {
		return completeOrderList;
	}
	public void setCompleteOrderList(List<BookedOrder> completeOrderList) {
		this.completeOrderList = completeOrderList;
	}
	public List<BookedOrder> getReadyOrderList() {
		return readyOrderList;
	}
	public void setReadyOrderList(List<BookedOrder> readyOrderList) {
		this.readyOrderList = readyOrderList;
	}
	public List<BookedOrder> getCancelOrderList() {
		return cancelOrderList;
	}
	public void setCancelOrderList(List<BookedOrder> cancelOrderList) {
		this.cancelOrderList = cancelOrderList;
	}
	
	
	
	
	
}
