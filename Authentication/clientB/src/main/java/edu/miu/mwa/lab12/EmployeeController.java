package edu.miu.mwa.lab12;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@GetMapping("/name")
	public String getName() {
		return "Frank Brown";
	}
	
	@GetMapping("/phone")
	public String getPhone() {
		return "645322899";
	}
}
