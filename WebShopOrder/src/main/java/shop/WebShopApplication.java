package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import shop.customers.service.interfaces.CustomerService;


@SpringBootApplication
public class WebShopApplication{
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}
	
	@Bean
	CustomerService CreateCustomerService() {
		return new shop.customers.service.client.CustomerService();
	}	
}
