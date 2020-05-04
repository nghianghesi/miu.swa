package edu.miu.mwa.lab12;

import org.springframework.web.bind.annotation.GetMapping;

public class EmployeeController {
	@GetMapping("/salary")
	public String getGetSalary() {
		return "95.000";
	}
}
