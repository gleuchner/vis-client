package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.CategoryDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager{
	private CategoryDAO helper;
	
	public CategoryManagerImpl() {
		helper = new CategoryDAO();
	}

	public List<Category> getCategories() {
		return helper.getObjectList();
	}

	public Category getCategory(int id) {
		return helper.getObjectById(id);
	}


	public void addCategory(String name, int userId) {
		Category cat = new Category(name);
		helper.saveObject(cat, userId);

	}

	public void delCategoryById(int id, int userId) {
		
		helper.deleteById(id, userId);
	}
}
