package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({"shop.products.service.feign", "shop.order.service.feign"})
public class WebShopApplication{
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}
}
