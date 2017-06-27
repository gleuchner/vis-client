package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryDAO {
    private static final String CATEGORY_BASE_URL = "http://localhost:8762/category-api/categories";

    public List<Category> getObjectList() {
        Category[] categories = RestTemplateProvider.getRestTemplate().getForObject(CATEGORY_BASE_URL, Category[].class);

        return Arrays.asList(categories);
    }

    public Category getObjectById(int id) {
        return RestTemplateProvider.getRestTemplate().getForObject(CATEGORY_BASE_URL + "/" + String.valueOf(id), Category.class);
    }

    public void saveObject(Category cat, int userId) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL);
//        RestTemplateProvider.getRestTemplate().postForObject(builder.build().encode().toUri(), cat, Category.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("userId", Integer.toString(userId));

        HttpEntity<Category> entity = new HttpEntity<Category>(cat,headers);
        RestTemplateProvider.getRestTemplate().postForObject(CATEGORY_BASE_URL, entity, Category.class);


    }

    public void deleteById(int id, int userId) {
        HttpEntity<HttpHeaders> request = new HttpEntity<>(createHeaderWithUserId(userId));
        RestTemplateProvider.getRestTemplate().exchange(CATEGORY_BASE_URL + "/{categoryId}", HttpMethod.DELETE, request, Void.class, id);
    }

    static HttpHeaders createHeaderWithUserId(final int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", Integer.toString(id));
        return headers;
    }
}
