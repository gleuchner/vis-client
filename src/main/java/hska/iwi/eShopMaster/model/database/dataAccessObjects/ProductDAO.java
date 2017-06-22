package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.ArrayList;
import java.util.List;

import hska.iwi.eShopMaster.configuration.RestTemplateProvider;
import org.apache.commons.lang3.ObjectUtils;

import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;


public class ProductDAO {
	private static final String PRODUCT_BASE_URL = "http://localhost:8762/product-api/products";

	public List<Product> getProductListByCriteria(String searchDescription,
												  Double searchMinPrice, Double searchMaxPrice) {

		ArrayList<Product> products = new ArrayList<>();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
				.queryParam("searchString", searchDescription).queryParam("minPrice", searchMinPrice)
				.queryParam("maxPrice", searchMaxPrice);

		products = RestTemplateProvider.getRestTemplate().getForObject(builder.build().encode().toUri(),
				products.getClass());

		return products;
	}


	public void deleteById(int id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL).pathSegment(String.valueOf(id));
		RestTemplateProvider.getRestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.DELETE, null, Void.class);
	}

	public void saveObject(Product product) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL);
		RestTemplateProvider.getRestTemplate().postForObject(builder.build().encode().toUri(), product, Product.class);
	}


	public Product getObjectById(int id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
				.pathSegment(String.valueOf(id));
		return RestTemplateProvider.getRestTemplate().getForObject(builder.build().encode().toUri(), Product.class);
	}

	public List<Product> getObjectList() {
		ArrayList<Product> products = new ArrayList<>();
		products = RestTemplateProvider.getRestTemplate().getForObject(PRODUCT_BASE_URL, products.getClass());
		return products;
	}
}
