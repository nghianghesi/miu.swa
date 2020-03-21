package shop.products.service.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import shop.products.service.ProductDTO;

public class ProductCatalogService implements shop.products.service.interfaces.ProductCatalogService{	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.product}") 
	String productServiceUrl;
	
	@Override
	public void addProduct(String productnumber, String description, double price) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productnumber", productnumber);
		params.put("description", description);
		params.put("productnumber", "" + price);
		restTemplate.postForEntity(this.productServiceUrl+"/product/{productnumber}/{description}/{price}", null, ProductDTO.class, params).getBody();
		return;
	}

	@Override
	public ProductDTO getProduct(String productnumber) {
		return restTemplate.getForEntity(this.productServiceUrl+"/product/101", ProductDTO.class).getBody();
	}

	@Override
	public void setStock(String productnumber, int quantity, String locationcode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("productnumber", productnumber);
		params.put("quantity", "" + quantity);
		params.put("locationcode", locationcode);
		restTemplate.getForEntity(this.productServiceUrl+"/product/stock/{productnumber}/{quantity}/{locationcode}", ProductDTO.class, params).getBody();
		return;
	}
}
