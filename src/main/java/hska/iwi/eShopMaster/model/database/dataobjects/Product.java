package hska.iwi.eShopMaster.model.database.dataobjects;



/**
 * This class contains details about products.
 */
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productId;

	private String name;

	private double price;

	private int categoryId;

	private String details;

	public Product() {
	}

	public Product(String name, double price, int category) {
		this.name = name;
		this.price = price;
		this.categoryId = category;
	}

	public Product(String name, double price, int category, String details) {
		this.name = name;
		this.price = price;
		this.categoryId = category;
		this.details = details;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int id) {
		this.productId = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int category) {
		this.categoryId = category;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
