package shop.order.service.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.NotAcceptableStatusException;

import shop.order.service.OrderDTO;
import shop.shopping.service.ShoppingCartDTO;

public class OrderService implements shop.order.service.interfaces.OrderService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.order}") 
	String orderServiceUrl;	
	
	@Override
	public OrderDTO getOrder(String orderNumber) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNumber", orderNumber);				
		return restTemplate.getForEntity(this.orderServiceUrl+"/order/{orderNumber}", OrderDTO.class, params).getBody();
	}
	
	@Override
	public void createOrder(ShoppingCartDTO shoppingCartDTO) {
		throw new NotAcceptableStatusException("Not supported via service call");		
	}
	
	@Override
	public void confirm(String orderNumber) {
		Map<String, String>params = new HashMap<String, String>();
		params.put("orderNumber", orderNumber);		
		restTemplate.postForEntity(this.orderServiceUrl+"/order/{orderNumber}", null, OrderDTO.class, params).getBody();
		return;
	}
	
	@Override
	public void setCustomer(String orderNumber, String customerNumber) {
		Map<String, String>params = new HashMap<String, String>();
		params.put("orderNumber", orderNumber);		
		params.put("customerNumber", customerNumber);
		restTemplate.postForEntity(this.orderServiceUrl+"/order/setCustomer/{orderNumber}/{customerNumber}", null, OrderDTO.class, params).getBody();		
		return;
	}
}
