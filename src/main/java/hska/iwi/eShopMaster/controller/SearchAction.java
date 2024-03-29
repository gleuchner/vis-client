package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.DisplayProduct;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565401833074694229L;
	
	
	private String searchDescription = null;
	private String searchMinPrice;
	private String searchMaxPrice;
	
	private Integer sMinPrice = null;
	private Integer sMaxPrice = null;
	
	private User user;
	private List<Product> storedProducts;
	private List<DisplayProduct> products  = new ArrayList<>();
	private List<Category> categories;
	

	public String execute() throws Exception {
		
		String result = "input";
		
		// Get user:
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		ActionContext.getContext().setLocale(Locale.US);  
		
		if(user != null){
			// Search products and show results:
			ProductManager productManager = new ProductManagerImpl();
//			this.products = productManager.getProductsForSearchValues(this.searchDescription, this.searchMinPrice, this.searchMaxPrice);
			if (!searchMinPrice.isEmpty()){
				Double tmp = Double.parseDouble(this.searchMinPrice) * 100;
				sMinPrice = tmp.intValue();
			}
			if (!searchMaxPrice.isEmpty()){
				Double tmp = Double.parseDouble(this.searchMaxPrice) * 100;
				sMaxPrice = tmp.intValue();
			}
			this.storedProducts = productManager.getProductsForSearchValues(this.searchDescription, sMinPrice, sMaxPrice);
			
			// Show all categories:
			CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();

			for (Product product: this.storedProducts) {

				Category match = new Category("Error");
				for (Category category: this.categories) {
					if(category.getId() == product.getCategoryId()){
						match = category;
						break;
					}
				}
				products.add(new DisplayProduct(product.getProductId(),product.getName(),product.getPrice(), match ,product.getDetails()));
			}

			result = "success";
		}
		
		return result;
	}
			
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		public List<DisplayProduct> getProducts() {
			return products;
		}

		public void setProducts(List<DisplayProduct> products) {
			this.products = products;
		}
		
		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		
		


	public String getSearchValue() {
		return searchDescription;
	}


	public void setSearchValue(String searchValue) {
		this.searchDescription = searchValue;
	}


	public String getSearchMinPrice() {
		return searchMinPrice;
	}


	public void setSearchMinPrice(String searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}


	public String getSearchMaxPrice() {
		return searchMaxPrice;
	}


	public void setSearchMaxPrice(String searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}


//	public Double getSearchMinPrice() {
//		return searchMinPrice;
//	}
//
//
//	public void setSearchMinPrice(Double searchMinPrice) {
//		this.searchMinPrice = searchMinPrice;
//	}
//
//
//	public Double getSearchMaxPrice() {
//		return searchMaxPrice;
//	}
//
//
//	public void setSearchMaxPrice(Double searchMaxPrice) {
//		this.searchMaxPrice = searchMaxPrice;
//	}
}
