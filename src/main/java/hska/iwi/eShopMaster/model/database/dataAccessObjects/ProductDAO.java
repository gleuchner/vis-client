package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import org.apache.commons.lang3.ObjectUtils;

import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;


public class ProductDAO {
    private static final String PRODUCT_BASE_URL = "http://zuul:8762/product-api/products";

    public List<Product> getProductListByCriteria(String searchDescription,
                                                  Integer searchMinPrice, Integer searchMaxPrice) {

        ArrayList<Product> products = new ArrayList<>();

        final String _query;
        if (searchDescription == null) _query = "";
        else _query = searchDescription.trim();

        final Integer _minPrice;
        if (searchMinPrice == null) _minPrice = Integer.MIN_VALUE;
        else _minPrice = searchMinPrice;

        final Integer _maxPrice;
        if (searchMaxPrice == null) _maxPrice = Integer.MAX_VALUE;
        else _maxPrice = searchMaxPrice;

        final URI productsURI = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
                .queryParam("query", _query)        //
                .queryParam("minPrice", _minPrice)  //
                .queryParam("maxPrice", _maxPrice)  //
                .build()                                  //
                .encode()                                 //
                .toUri();                                 //

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
                .queryParam("query", searchDescription).queryParam("minPrice", searchMinPrice)
                .queryParam("maxPrice", searchMaxPrice);

        products = RestTemplateProvider.getRestTemplate().getForObject(builder.build().encode().toUri(),
                products.getClass());

        return products;
    }


    public void deleteById(int id, int userId) {
        HttpEntity<HttpHeaders> request = new HttpEntity<>(createHeaderWithUserId(userId));
        RestTemplateProvider.getRestTemplate().exchange(PRODUCT_BASE_URL + "/{productId}", HttpMethod.DELETE, request, Void.class, id);
    }

    public Product saveObject(Product product, int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("userId", Integer.toString(userId));

        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
        return RestTemplateProvider.getRestTemplate().postForObject(PRODUCT_BASE_URL, entity, Product.class);
    }


    public Product getObjectById(int id) {
        return RestTemplateProvider.getRestTemplate().getForObject(PRODUCT_BASE_URL + "/" + String.valueOf(id), Product.class);
    }

    public List<Product> getObjectList() {

        Product[] products = RestTemplateProvider.getRestTemplate().getForObject(PRODUCT_BASE_URL, Product[].class);
        return Arrays.asList(products);
    }

    static HttpHeaders createHeaderWithUserId(final int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", Integer.toString(id));
        return headers;
    }

}
