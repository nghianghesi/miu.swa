package shop.customers.service.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.NotAcceptableStatusException;

import shop.customers.service.CustomerDTO;
import shop.customers.service.OrderCustomerDTO;

public interface CustomerService {
	@PostMapping("/customer")
	public void addCustomer(CustomerDTO customer);
	
	@GetMapping("/customer/{customerNumber}")
	public CustomerDTO getCustomer(String customerNumber) ;	
	
	default public OrderCustomerDTO getOrderCustomer(String customerNumber) {
		throw new NotAcceptableStatusException("Not supported");
	}
}
