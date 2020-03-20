package shop.order.service;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCartDTO {
	String cartid;
	
	ArrayList<CartLineDTO> cartlineList = new ArrayList<CartLineDTO>();

	public void addToCart(ProductDTO product, int quantity) {
		for (CartLineDTO cline : cartlineList) {
			if (cline.getProduct().getProductnumber().equals(product.getProductnumber())) {
				cline.setQuantity(cline.getQuantity()+quantity);
				return;
			}
		}
		CartLineDTO cline = new CartLineDTO();
		cline.setProduct(product);
		cline.setQuantity(quantity);
		cartlineList.add(cline);
	}

	public void print() {
		System.out.println("Content of the shoppingcart:");
		for (CartLineDTO cline : cartlineList) {
			System.out.println(cline.getQuantity() + " "
					+ cline.getProduct().getProductnumber() + " "
					+ cline.getProduct().getDescription() + " "
					+ cline.getProduct().getPrice());
		}
		System.out.println("Total price ="+getTotalPrice());
	}
	
	public double getTotalPrice(){
		double totalPrice = 0.0;
		for (CartLineDTO cline : cartlineList) {
			totalPrice=totalPrice+(cline.getProduct().getPrice() * cline.getQuantity());
		}
		return totalPrice;
	}
	
	public void removeFromCart(ProductDTO product){
		Iterator<CartLineDTO> iter = cartlineList.iterator();
		while (iter.hasNext()){
			CartLineDTO cline = iter.next();
			if (cline.getProduct().getProductnumber().equals(product.getProductnumber())){
				if (cline.getQuantity()>1){
					cline.setQuantity(cline.getQuantity()-1);
				}
				else{
					iter.remove();
				}
			}
		}
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public ArrayList<CartLineDTO> getCartlineList() {
		return cartlineList;
	}

	public void setCartlineList(ArrayList<CartLineDTO> cartlineList) {
		this.cartlineList = cartlineList;
	}

}
