package shop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import shop.customers.service.AddressDTO;
import shop.customers.service.CustomerDTO;
import shop.order.service.OrderDTO;
import shop.products.service.ProductDTO;
import shop.shopping.service.ShoppingCartDTO;

@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {
	@Autowired
	private RestOperations  restTemplate;	
	
	@Value( "${api.customer}" )
	private String  customerServiceUrl;
	
	@Value( "${api.product}" )
	private String  productServiceUrl;
	
	@Value( "${api.order}" )
	private String  orderServiceUrl;
	
	@Value( "${api.shopping}" )
	private String  shoppingServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		//create customer
		CustomerDTO customerDto = new CustomerDTO("101","Frank","Brown","fBrown@Hotmail.com","123456");
		AddressDTO addressDTO = new AddressDTO("1000 N main Street", "Fairfield","52557","USA");
		customerDto.setAddress(addressDTO);
		//call the customer component to add the customer
		restTemplate.postForEntity(this.customerServiceUrl+"/account", customerDto, CustomerDTO.class);

		// get customer
		//call the customer component to get the customer with id 101 and print the result
		customerDto = restTemplate.getForEntity(this.customerServiceUrl+"/account/101", CustomerDTO.class).getBody();
		System.out.println(customerDto);
		
		//create products	
		//call the product component to create the first product
		Map<String, String> params = new HashMap<String, String>();
		params.put("productnumber", "101");
		params.put("description", "Test 101");
		params.put("productnumber", "101");
		ProductDTO product = restTemplate.postForEntity(this.productServiceUrl+"/product/{productnumber}/{description}/{price}", null, ProductDTO.class, params).getBody();
		
		//call the product component to create the second product 
		params = new HashMap<String, String>();
		params.put("productnumber", "102");
		params.put("description", "Test 102");
		params.put("productnumber", "102");
		product = restTemplate.postForEntity(this.productServiceUrl+"/product/{productnumber}/{description}/{price}", null, ProductDTO.class, params).getBody();

		//set stock	
		//call the product component to set the stock for the first product
		params = new HashMap<String, String>();
		params.put("productnumber", "101");
		params.put("quantity", "10");
		params.put("locationcode", "loc 101");
		product = restTemplate.getForEntity(this.productServiceUrl+"/product/stock/{productnumber}/{quantity}/{locationcode}", ProductDTO.class, params).getBody();

		//get a product
		//call the product component to get the the first product and print the result
		product = restTemplate.getForEntity(this.productServiceUrl+"/product/101", ProductDTO.class).getBody();
		System.out.println(product);

		// add products to the shoppingcart
		//call the shopping component to add the first product to the cart
		params = new HashMap<String, String>();
		params.put("cartId", "101");		
		params.put("productnumber", "101");				
		params.put("quantity", "2");
		ShoppingCartDTO shopcart = restTemplate.postForEntity(this.shoppingServiceUrl+"/cart/{cartId}/{productnumber}/{quantity}", null, ShoppingCartDTO.class, params).getBody();
		
		//call the shopping component to add the second product to the cart
		params = new HashMap<String, String>();
		params.put("cartId", "101");		
		params.put("productnumber", "102");				
		params.put("quantity", "2");
		shopcart = restTemplate.postForEntity(this.shoppingServiceUrl+"/cart/{cartId}/{productnumber}/{quantity}", null, ShoppingCartDTO.class, params).getBody();


		//get the shoppingcart
		//call the shopping component to get the cart and print the result
		params = new HashMap<String, String>();
		params.put("cartId", "101");		
		shopcart = restTemplate.getForEntity(this.shoppingServiceUrl+"/cart/{cartId}", ShoppingCartDTO.class, params).getBody();

		//checkout the cart		
		//call the shopping component to checkout the cart 
		params = new HashMap<String, String>();
		params.put("cartId", "101");		
		shopcart = restTemplate.getForEntity(this.shoppingServiceUrl+"/cart/checkout/{cartId}", ShoppingCartDTO.class, params).getBody();		
		System.out.println(shopcart);

		//get the order
		//call the order component to get the order and print the result
		params = new HashMap<String, String>();
		params.put("orderNumber", "101");				
		OrderDTO order = restTemplate.getForEntity(this.orderServiceUrl+"/order/{orderNumber}", OrderDTO.class, params).getBody();		
		
		//add customer to order
		//call the order component to add a customer to the order
		params = new HashMap<String, String>();
		params.put("orderNumber", "101");		
		params.put("customerNumber", "101");
		order = restTemplate.postForEntity(this.orderServiceUrl+"/order/setCustomer/{orderNumber}/{customerNumber}", null, OrderDTO.class, params).getBody();		
			
		//confirm the order
		//call the order component to confirm the order
		params = new HashMap<String, String>();
		params.put("orderNumber", "101");		
		order = restTemplate.postForEntity(this.orderServiceUrl+"{orderNumber}", null, OrderDTO.class, params).getBody();		
		
		//get the order
		//call the order component to get the order and print the result
		params = new HashMap<String, String>();
		params.put("orderNumber", "101");		
		order = restTemplate.getForEntity(this.orderServiceUrl+"{orderNumber}", OrderDTO.class, params).getBody();				
		System.out.println(order);
	}
}
