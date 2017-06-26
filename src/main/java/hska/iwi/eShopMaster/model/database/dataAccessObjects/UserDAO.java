package hska.iwi.eShopMaster.model.database.dataAccessObjects;


import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;


public class UserDAO  {

    private static final String USER_BASE_URL = "http://localhost:8762/user-api/users";

    private boolean isForRegister;

    public UserDAO(boolean isForRegister){
        this.isForRegister = isForRegister;
    }

	public User getUserByUsername(String name) {
        ResponseEntity<User> response = null;

        // we have no token for this user
//        if (RestTemplateProvider.getRestTemplate() == null) {
//            return getUserByUsernameWithoutToken(name);
//        }

        try {
            OAuth2RestTemplate restTemplate = null;
            if(isForRegister){
                restTemplate = RestTemplateProvider.getRestTemplateForRegister();
            }else{
                restTemplate = RestTemplateProvider.getRestTemplate();
            }
            User[] users = restTemplate.getForObject(USER_BASE_URL, User[].class);
            for (User user :users) {
                if (user.getUsername().equals(name)) {
                    response = new ResponseEntity<User>(user, HttpStatus.OK);
                    break;
                }
            }
        } catch (OAuth2AccessDeniedException e) {
            return null;
        }

        if(response != null){
            return response.getBody();
        }

        return null;
	}


    public User saveObject(User user) {

//        String jsonStringUser = "{"
//                + "\"userId\":2" + ","
//                + "\"firstname\":\" firstname\"" + ","
//                + "\"name\":\"name\"" + ","
//                + "\"username\":\"username\"" + ","
//                + "\"password\":\"password\"" + ","
//                + "\"role\":0"
//                + "}";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType( MediaType.APPLICATION_JSON );
//
//        HttpEntity request= new HttpEntity( jsonStringUser, headers );

        OAuth2RestTemplate restTemplate = RestTemplateProvider.getRestTemplateForRegister();
        User response = restTemplate.postForObject(USER_BASE_URL, user, User.class);

        if (response == null) {
            User user2 = response;
            return user2;
        }
        System.out.println("Register user code: " + response);
        return null;
    }

    public void deleteObject(User user) {
        // TODO is this needed?
        System.err.println("");
    }
}
