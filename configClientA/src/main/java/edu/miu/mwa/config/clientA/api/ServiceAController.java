package edu.miu.mwa.config.clientA.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
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
	 
	 @RequestMapping("/ba")
	 public String getBAGreeting() {
		 return this.serviceB.getAGreeting();
	 }
	 
	 public static interface ServiceB{
		 @GetMapping()
		 String getGreeting();
		 @GetMapping("/a")
		 String getAGreeting();
	 }
	 
	 @ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
	 @FeignClient(name="ServiceB")
	 public static interface FeignClientServiceB extends ServiceB{
	 }	 
	 
	 @ConditionalOnProperty(name = "feignclientsource", havingValue="api", matchIfMissing = false)
	 @FeignClient(name="ApiServer", path="/sb")
	 public static interface ApiServiceB extends ServiceB{
		 @GetMapping()
		 String getGreeting();
	 }
}
