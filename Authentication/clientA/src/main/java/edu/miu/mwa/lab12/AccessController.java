package edu.miu.mwa.lab12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;

public class AccessController {
	
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	@GetMapping("/productdata")
	public String getProductdata() {
		return "95.000";
	}
	
	@GetMapping("/employee")
	public String getEmployee() {
		String name = this.oAuth2RestTemplate.getForEntity("http://localhost:8091/name", String.class).getBody();
		String phone = this.oAuth2RestTemplate.getForEntity("http://localhost:8091/phone", String.class).getBody();
		return name + " " + phone;
	}
	
	@GetMapping("/salary")
	public String getSalary() {
		return this.oAuth2RestTemplate.getForEntity("http://localhost:8092/salary", String.class).getBody();
	}
}
