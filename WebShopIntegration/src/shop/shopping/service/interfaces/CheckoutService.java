package shop.shopping.service.interfaces;

import shop.shopping.service.ShoppingCartDTO;

public interface CheckoutService {
	public ShoppingCartDTO checkout(String cartId) ;
}
