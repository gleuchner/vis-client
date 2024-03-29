package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {
	UserDAO helper;
	
	public UserManagerImpl(boolean isForRegister) {
		helper = new UserDAO(isForRegister);
	}

	
	public void registerUser(String username, String name, String lastname, String password, int role) {

		User user = new User(username, name, lastname, password, role);

		helper.saveObject(user);
	}

	
	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		return helper.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		User user = new User();
		user.setUserId(id);
		helper.deleteObject(user);
		return true;
	}

	public int getRoleByLevel(int level) {
		return level;
	}

	public boolean doesUserAlreadyExist(String username) {
		
    	User dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getName() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}

}
