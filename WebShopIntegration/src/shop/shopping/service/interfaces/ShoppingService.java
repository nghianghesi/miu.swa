package shop.shopping.service.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.shopping.service.ShoppingCartDTO;

public interface ShoppingService {
	
	@PostMapping("/cart/{cartId}/{productnumber}/{quantity}")
	public void addToCart(String cartId, String productnumber, int quantity);
	
	@GetMapping("/cart/{cartId}")
	public ShoppingCartDTO getCart(String cartId);
}
