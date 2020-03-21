package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import shop.order.service.interfaces.OrderService;
import shop.products.service.interfaces.ProductCatalogService;

@SpringBootApplication
public class WebShopApplication{
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}
	
	@Bean
	ProductCatalogService CreateProductServiceClient() {
		return new shop.products.service.client.ProductCatalogService();
	}
	
	@Bean
	OrderService CreateOrderServiceClient() {
		return new shop.order.service.client.OrderService();
	}	
}
