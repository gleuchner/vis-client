package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;


public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, int role);
    
    public User getUserByUsername(String username);
    
    public boolean deleteUserById(int id);
    
    public int getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);
}
