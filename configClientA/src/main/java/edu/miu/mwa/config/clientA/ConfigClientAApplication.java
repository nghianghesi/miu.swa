package edu.miu.mwa.config.clientA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConfigClientAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientAApplication.class, args);
	}
}
