package edu.miu.mwa.config.clientB.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
}
