package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final String CATEGORY_BASE_URL = "http://localhost:8762/product-api/categories";

    public List<Category> getObjectList() {
        List<Category> categories = new ArrayList<>();

        categories = RestTemplateProvider.getRestTemplate().getForObject(CATEGORY_BASE_URL, categories.getClass());

        return categories;
    }

    public Category getObjectById(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL).pathSegment(String.valueOf(id));
        return RestTemplateProvider.getRestTemplate().getForObject(builder.build().encode().toUri(), Category.class);
    }

    public void saveObject(Category cat) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL);
        RestTemplateProvider.getRestTemplate().postForObject(builder.build().encode().toUri(), cat, Category.class);
    }

    public void deleteById(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL)
                .pathSegment(String.valueOf(id));

        RestTemplateProvider.getRestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.DELETE, null,
                Void.class);
    }
}
