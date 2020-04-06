package shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import shop.customers.service.AddressDTO;
import shop.customers.service.CustomerDTO;
import shop.customers.service.interfaces.CustomerService;
import shop.order.service.OrderDTO;
import shop.order.service.interfaces.OrderService;
import shop.products.service.ProductDTO;
import shop.products.service.interfaces.ProductCatalogService;
import shop.shopping.service.interfaces.CheckoutService;
import shop.shopping.service.interfaces.ShoppingService;

@SpringBootApplication
@EnableFeignClients({
	"shop.customers.service.feign",
	"shop.products.service.feign", 
	"shop.order.service.feign",
	"shop.shopping.service.feign"
})
public class WebShopApplication implements CommandLineRunner {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductCatalogService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShoppingService shoppingService;
	
	@Autowired
	CheckoutService checkoutService;	

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
		this.customerService.addCustomer(customerDto);

		// get customer
		//call the customer component to get the customer with id 101 and print the result
		customerDto = this.customerService.getCustomer("101");
		System.out.println(customerDto);
		
		//create products	
		//call the product component to create the first product
		this.productService.addProduct("101", "Test 101", 101);
		
		//call the product component to create the second product 
		this.productService.addProduct("102", "Test 102", 102);

		//set stock	
		//call the product component to set the stock for the first product
		this.productService.setStock("101", 10, "loc 101");

		//get a product
		//call the product component to get the the first product and print the result
		ProductDTO product = this.productService.getProduct("101");
		System.out.println(product);

		// add products to the shopping cart
		//call the shopping component to add the first product to the cart
		this.shoppingService.addToCart("101", "101", 2);
		
		//call the shopping component to add the second product to the cart
		this.shoppingService.addToCart("101", "102", 2);

		//get the shopping cart
		//call the shopping component to get the cart and print the result
		this.shoppingService.getCart("101");
		//checkout the cart		
		//call the shopping component to checkout the cart 
		this.checkoutService.checkout("101");		

		//get the order
		//call the order component to get the order and print the result
		OrderDTO order = this.orderService.getOrder("101");		
		
		//add customer to order
		//call the order component to add a customer to the order
		this.orderService.setCustomer("101", "101");
		
		//confirm the order
		//call the order component to confirm the order
		this.orderService.confirm("101");
		
		//get the order
		//call the order component to get the order and print the result
		order = this.orderService.getOrder("101");
		System.out.println(order);
	}
}
