package shop.customers.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.NotAcceptableStatusException;

import shop.customers.service.CustomerDTO;
import shop.customers.service.OrderCustomerDTO;

@FeignClient("WebShopCustomer")
public interface CustomerService extends shop.customers.service.interfaces.CustomerService{
	@PostMapping("/customer")
	public void addCustomer(CustomerDTO customer);
	
	@GetMapping("/customer/{customerNumber}")
	public CustomerDTO getCustomer(String customerNumber) ;
	
	
	default public OrderCustomerDTO getOrderCustomer(String customerNumber) {
		throw new NotAcceptableStatusException("Not supported");
	}
}
