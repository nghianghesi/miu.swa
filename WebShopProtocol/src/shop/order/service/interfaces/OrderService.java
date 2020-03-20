package shop.order.service.interfaces;

import shop.order.service.OrderDTO;
import shop.shopping.service.ShoppingCartDTO;

public interface OrderService {
	public OrderDTO getOrder(String orderNumber);
	public void createOrder(ShoppingCartDTO shoppingCartDTO);
	public void confirm(String orderNumber);
	public void setCustomer(String orderNumber, String customerNumber);
}
