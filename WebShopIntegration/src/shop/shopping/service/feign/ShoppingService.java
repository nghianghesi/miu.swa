package shop.shopping.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.shopping.service.ShoppingCartDTO;
import shop.shopping.service.interfaces.CheckoutService;

@FeignClient("WebShopShopping")
public interface ShoppingService extends shop.shopping.service.interfaces.ShoppingService, CheckoutService{
	
	@PostMapping("/cart/{cartId}/{productnumber}/{quantity}")
	public void addToCart(String cartId, String productnumber, int quantity);
	
	@GetMapping("/cart/{cartId}")
	public ShoppingCartDTO getCart(String cartId);
	
	@PostMapping("/cart/checkout/{cartId}")
	public ShoppingCartDTO checkout(String cartId);
}
