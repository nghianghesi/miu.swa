package edu.miu.mwa.config.clientB.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {
	
	 @Autowired
	 ServiceA serviceA;
	
	 @Value("${greeting}")
	 private String message;
	 
	 @RequestMapping("/")
	 public String getName() {
		 return message;
	 }	 
	 
	 @RequestMapping("/a")
	 public String getAName() {
		 return this.serviceA.getGreeting();
	 }
	 
	 public static interface ServiceA{
		 @GetMapping("/")
		 public String getGreeting();
	 }	 
	 
	 @ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
	 @FeignClient("ServiceA")
	 public static interface FeignServiceA extends ServiceA{
	 }	
	 
	 @ConditionalOnProperty(name = "feignclientsource", havingValue="api", matchIfMissing = true)
	 @FeignClient(name="ApiServer", path="/sa")
	 public static interface ApiServiceA extends ServiceA{
	 }
}
