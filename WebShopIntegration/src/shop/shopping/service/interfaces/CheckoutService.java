package shop.shopping.service.interfaces;

import org.springframework.web.bind.annotation.PostMapping;

import shop.shopping.service.ShoppingCartDTO;

public interface CheckoutService {
	
	@PostMapping("/cart/checkout/{cartId}")
	public ShoppingCartDTO checkout(String cartId);
}
