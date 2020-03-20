package shop.shopping.service.interfaces;

import shop.shopping.service.ShoppingCartDTO;

public interface ShoppingService {
	public void addToCart(String cartId, String productnumber, int quantity);
	public ShoppingCartDTO getCart(String cartId);
}
