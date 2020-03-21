package shop.shopping.service.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import shop.shopping.service.ShoppingCartDTO;

public class ShoppingService implements shop.shopping.service.interfaces.ShoppingService{
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.shopping}") 
	String shoppingServiceUrl;	
	
	
	@Override
	public void addToCart(String cartId, String productnumber, int quantity) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cartId", cartId);		
		params.put("productnumber", productnumber);				
		params.put("quantity", ""+quantity);
		restTemplate.postForEntity(this.shoppingServiceUrl+"/cart/{cartId}/{productnumber}/{quantity}", null, ShoppingCartDTO.class, params).getBody();
		return;
	}

	@Override
	public ShoppingCartDTO getCart(String cartId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cartId", cartId);		
		return restTemplate.getForEntity(this.shoppingServiceUrl+"/cart/{cartId}", ShoppingCartDTO.class, params).getBody();
	}
	
	public ShoppingCartDTO checkout(String cartId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cartId", cartId);		
		return restTemplate.getForEntity(this.shoppingServiceUrl+"/cart/checkout/{cartId}", ShoppingCartDTO.class, params).getBody();
	}
}
