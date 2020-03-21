package shop.customers.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.NotAcceptableStatusException;

import shop.customers.service.CustomerDTO;
import shop.customers.service.OrderCustomerDTO;

public class CustomerService implements shop.customers.service.interfaces.CustomerService{
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.customer}") 
	String customerServiceUrl;
	
	@Override
	public void addCustomer(CustomerDTO customer) {
		restTemplate.postForEntity(this.customerServiceUrl+"/account", customer, CustomerDTO.class);
	}

	@Override
	public CustomerDTO getCustomer(String customerNumber) {
		return restTemplate.getForEntity(this.customerServiceUrl+"/account/" + customerNumber, CustomerDTO.class).getBody();		
	}

	@Override
	public OrderCustomerDTO getOrderCustomer(String customerNumber) {
		throw new NotAcceptableStatusException("Not supported");
	}

}
