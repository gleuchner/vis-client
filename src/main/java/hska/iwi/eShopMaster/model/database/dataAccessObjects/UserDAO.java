package hska.iwi.eShopMaster.model.database.dataAccessObjects;


import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
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
            OAuth2RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
            User[] users = restTemplate.getForObject(USER_BASE_URL, User[].class);
            for (User user :users) {
                if (user.getUserName().equals(name)) {
                    response = new ResponseEntity<User>(user, HttpStatus.OK);
                    break;
                }
            }
        } catch (OAuth2AccessDeniedException e) {
            return null;
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
