package deryl.jibin.clyde.ovenfresh.entity;

public class OrderProduct {

	private String productId;
	private String productName;
	private String productPrice;
	private String productQuantity;
	private String productItemCount;
	private String productActualPrice;
	private String productCategory;
	
	public OrderProduct(String productId, String productName, String productPrice,
			String productQuantity, String productItemCount, String productActualPrice, String productCategory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productItemCount = productItemCount;
		this.productActualPrice = productActualPrice;
		this.productCategory = productCategory;
	}

	
	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}




	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getProductActualPrice() {
		return productActualPrice;
	}


	public void setProductActualPrice(String productActualPrice) {
		this.productActualPrice = productActualPrice;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}


	public String getProductItemCount() {
		return productItemCount;
	}


	public void setProductItemCount(String productItemCount) {
		this.productItemCount = productItemCount;
	}
	
	
	
	
	
}
