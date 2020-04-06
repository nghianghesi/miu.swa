package edu.miu.mwa.config.clientA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

import edu.miu.mwa.config.clientA.api.ServiceAController.ServiceB;
import feign.Feign;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConfigClientAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientAApplication.class, args);
	}
	
	@Value("${api.url}")
	String apiUrl;
	
	@Bean
	ServiceB getServiceB() {
		return Feign.builder().contract(new SpringMvcContract())
        .target(ServiceB.class, this.apiUrl+"sb");
	}
}
