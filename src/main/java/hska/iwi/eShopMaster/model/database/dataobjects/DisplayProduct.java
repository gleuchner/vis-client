package hska.iwi.eShopMaster.model.database.dataobjects;



/**
 * This class contains details about products.
 */
public class DisplayProduct implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int productId;

	private String name;

	private double price;

	private Category category;

	private String details;

	public DisplayProduct() {
	}

	public DisplayProduct(String name, double price, Category category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public DisplayProduct(int id, String name, double price, Category category, String details) {
		this.productId=id;
		this.name = name;
		this.price = price;
		this.category = category;
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
