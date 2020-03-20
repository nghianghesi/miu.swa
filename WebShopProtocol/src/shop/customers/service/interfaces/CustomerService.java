package shop.customers.service.interfaces;

import shop.customers.service.CustomerDTO;
import shop.customers.service.OrderCustomerDTO;

public interface CustomerService {
	public void addCustomer(CustomerDTO customer);
	public CustomerDTO getCustomer(String customerNumber) ;
	public OrderCustomerDTO getOrderCustomer(String customerNumber);
}
