package deryl.jibin.clyde.ovenfresh.entity;

import java.util.HashMap;
import java.util.List;

public class ProductList {
	
	private static ProductList instance;
	private HashMap<String, List<Product>> productList;
	public static ProductList getInstance() {
		if(instance == null)
		{
			instance = new ProductList();
		}
		return instance;
	}
	public static void setInstance(ProductList instance) {
		instance = null;
	}
	
	public HashMap<String, List<Product>> getProductList() {
		return productList;
	}
	public void setProductList(HashMap<String, List<Product>> productList) {
		this.productList = productList;
	}
	
	
}
