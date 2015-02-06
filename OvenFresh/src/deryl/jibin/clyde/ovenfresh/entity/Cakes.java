package deryl.jibin.clyde.ovenfresh.entity;

public class Cakes {

	private String name;
	private String price;
	
	public Cakes()
	{}
	
	public Cakes(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
