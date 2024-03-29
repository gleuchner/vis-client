package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -983183915002226000L;
    private String username = null;
    private String password = null;
    private String firstname;
    private String lastname;
    private int role;


    @Override
    public String execute() throws Exception {

        // Return string:
        String result = "input";

        //retrieve token for credentials check
        RestTemplateProvider.createAndGetOAuth2RestTemplateForRegister();

        UserManager myCManager = new UserManagerImpl(true);

        // Get user from DB:
        User user = myCManager.getUserByUsername(getUsername());
        // Get session to save user role and login:
        if ((user == null) || !(user.getPassword().equals(this.getPassword()))) {
            addActionError("error.password.wrong");
            return result;
        }
        RestTemplateProvider.createAndGetOAuth2RestTemplate(user.getUsername(), user.getPassword(), user.getRole());

        Map<String, Object> session = ActionContext.getContext().getSession();

        // Save user object in session:
        session.put("webshop_user", user);
        session.put("message", "");
        firstname = user.getFirstname();
        lastname = user.getName();
        role = user.getRole();
        result = "success";
        return result;
    }

    @Override
    public void validate() {
        if (getUsername().length() == 0) {
            addActionError(getText("error.username.required"));
        }
        if (getPassword().length() == 0) {
            addActionError(getText("error.password.required"));
        }
    }

    public String getUsername() {
        return (this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
