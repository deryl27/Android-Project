package deryl.jibin.clyde.ovenfresh.entity;

import java.util.List;

public class CategoryList {
	
	private static CategoryList instance ;
	private List<Category> categoryList;
	
	
	public static CategoryList getInstance() 
	{
		if(instance == null){
			instance = new CategoryList();
		}
		return instance;
	}
	
	public static void setInstance(CategoryList instance) {
		instance = null;
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}
	
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	
	
	
	

}
