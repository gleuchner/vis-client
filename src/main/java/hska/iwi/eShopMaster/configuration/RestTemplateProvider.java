package hska.iwi.eShopMaster.configuration;

/**
 * Created by henrikhierl on 22.06.17.
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class RestTemplateProvider {
    private static final String AUTH_BASE_URL = "http://auth-server:8763";
    public static final String TOKEN_URI = AUTH_BASE_URL + "/oauth/token";
    public static final String CLIENT_ID = "my-client-with-secret";
    public static final String CLIENT_SECRET = "secret";
    private static OAuth2RestTemplate CONFIGURED_OAUTH2_REST_TEMPLATE;

    public static OAuth2RestTemplate getRestTemplate() {
        return CONFIGURED_OAUTH2_REST_TEMPLATE;
    }

    /**
     * Creates an OAuth2 rest template with grand type 'password' for the given
     * credentials.
     *
     * @param username
     *            the username credential
     * @param password
     *            the password credential
     * @return the configured OAuth2 rest template
     */
    public static OAuth2RestTemplate createAndGetOAuth2RestTemplate(String username, String password) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        CONFIGURED_OAUTH2_REST_TEMPLATE = new OAuth2RestTemplate(createResource(username, password),
                new DefaultOAuth2ClientContext(atr));
        return CONFIGURED_OAUTH2_REST_TEMPLATE;
    }

    private static OAuth2ProtectedResourceDetails createResource(String username, String password) {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List<String> scopes = new ArrayList<String>(2);
        scopes.add("webshop");
        resource.setAccessTokenUri(TOKEN_URI);
        resource.setClientId(CLIENT_ID);
        resource.setClientSecret(CLIENT_SECRET);
        resource.setGrantType("password");
        resource.setScope(scopes);

        resource.setUsername(username);
        resource.setPassword(password);

        return resource;
    }
}
