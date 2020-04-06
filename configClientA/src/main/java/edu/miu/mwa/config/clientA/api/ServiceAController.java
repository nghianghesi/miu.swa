package edu.miu.mwa.config.clientA.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

	@Autowired
	ServiceB serviceB;
	
	 @Value("${greeting}")
	 private String greeting;
	 @Value("${message}")
	 private String message;
	 
	 @RequestMapping("/")
	 public String getName() {
		 return message + " " + greeting;
	 }
	 
	 @RequestMapping("/b")
	 public String getBGreeting() {
		 return this.serviceB.getGreeting();
	 }
	 
	 public static interface ServiceB{
		 @GetMapping()
		 String getGreeting();
	 }
}
