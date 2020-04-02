package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("shop.customers.service.feign")
public class WebShopApplication{
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}
}
