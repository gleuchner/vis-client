package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.List;

public interface ProductManager {

	public List<Product> getProducts();

	public Product getProductById(int id);

	public int addProduct(String name, double price, int categoryId, String details, int userId);

	public List<Product> getProductsForSearchValues(String searchValue, Integer searchMinPrice, Integer searchMaxPrice);

	public void deleteProductById(int id, int userId);
}
