package edu.miu.mwa.config.clientB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

import edu.miu.mwa.config.clientB.api.ServiceBController.ServiceA;
import feign.Feign;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConfigClientBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientBApplication.class, args);
	}
	
	@Value("${api.url}")
	String apiUrl;
	
	@Bean
	ServiceA getServiceA() {
		return Feign.builder().contract(new SpringMvcContract())
        .target(ServiceA.class, this.apiUrl+"sa");
	}
}
