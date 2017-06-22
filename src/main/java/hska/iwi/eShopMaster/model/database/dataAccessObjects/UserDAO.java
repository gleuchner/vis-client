package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.List;


import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;

import javax.ws.rs.core.Response;


public class UserDAO  {

    private static final String USER_BASE_URL = "http://localhost:8762/user-api/users";


	public User getUserByUsername(String name) {
        ResponseEntity<User> response = null;

        // we have no token for this user
//        if (RestTemplateProvider.getRestTemplate() == null) {
//            return getUserByUsernameWithoutToken(name);
//        }

        try {
            response = RestTemplateProvider.getRestTemplate().getForEntity(USER_BASE_URL + "/names/" + name, User.class);
        } catch (OAuth2AccessDeniedException e) {
            if (e.getCause().getMessage().equals("999")) {
                // return a null user => username not found
                return null;
            } else if (e.getCause().getMessage().equals("1001")) {
                // return a dummy user => password wrong
                User dummy = new User();
                return dummy;
            }
        }
        return response.getBody();
	}


    public void saveObject(User user) {
        Response response = RestConnectionHelper.postResponseForURL(USER_BASE_URL, user);
        if (response.getStatus() == 200) {
            User user2 = response.readEntity(User.class);
        //    return user2;
        }
        System.out.println("Register user code: " + response.getStatus());
       // return null;
    }

    public void deleteObject(User user) {
        // TODO is this needed?
        System.err.println("");
    }
}
