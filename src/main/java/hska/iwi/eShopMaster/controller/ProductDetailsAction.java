package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.DisplayProduct;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport {
	
	private User user;
	private int id;
	private String searchValue;
	private Integer searchMinPrice;
	private Integer searchMaxPrice;
	private Product storedproduct;
	private DisplayProduct product;
	private List<Category> categories;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7708747680872125699L;

	public String execute() throws Exception {

		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null) {
			ProductManager productManager = new ProductManagerImpl();
			storedproduct = productManager.getProductById(id);

			//
			CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();
			Category match = new Category("Error");
			for (Category category: this.categories) {
				if(category.getId() == storedproduct.getCategoryId()){
					match=category;
					break;
				}
			}
			product = new DisplayProduct(storedproduct.getProductId(),storedproduct.getName(), storedproduct.getPrice(), match, storedproduct.getDetails());

			res = "success";			
		}
		
		return res;		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getSearchMinPrice() {
		return searchMinPrice;
	}

	public void setSearchMinPrice(Integer searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}

	public Integer getSearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setSearchMaxPrice(Integer searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public DisplayProduct getProduct() {
		return product;
	}

	public void setProduct(DisplayProduct product) {
		this.product = product;
	}
}
