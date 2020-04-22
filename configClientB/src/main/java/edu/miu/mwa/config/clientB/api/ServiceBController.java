package edu.miu.mwa.config.clientB.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
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
	 
	 @FeignClient("ServiceA")
	 interface ServiceA{
		 @RequestMapping("/")
		 public String getGreeting();
	 }
}
