package shop.order.service.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.order.service.OrderDTO;
import shop.shopping.service.ShoppingCartDTO;

public interface OrderService {

	@GetMapping("/order/{orderNumber}")
	public OrderDTO getOrder(String orderNumber);
	
	@PostMapping("/order")
	public void createOrder(ShoppingCartDTO shoppingCartDTO);
	
	@PostMapping("/order/{orderNumber}")
	public void confirm(String orderNumber);
	
	@PostMapping("/order/setCustomer/{orderNumber}/{customerNumber}")
	public void setCustomer(String orderNumber, String customerNumber);
}
