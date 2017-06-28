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
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;
	
	User user;
	private List<Product> storedProducts;
	private List<DisplayProduct> products = new ArrayList<>();
	private List<Category> categories;
	
	public String execute() throws Exception{
		String result = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null){
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			this.storedProducts = productManager.getProducts();

			CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();

			for (Product procuct: storedProducts) {
				Category match = new Category("Error");
				for (Category category: this.categories) {
					if(category.getId() == procuct.getCategoryId()){
						match = category;
						break;
					}
				}
				products.add(new DisplayProduct(procuct.getProductId(),procuct.getName(),procuct.getPrice(), match ,procuct.getDetails()));
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

}
