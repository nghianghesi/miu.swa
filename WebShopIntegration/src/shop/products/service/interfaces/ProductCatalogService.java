package shop.products.service.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.products.service.ProductDTO;

public interface ProductCatalogService {
	
	@PostMapping("/product/{productnumber}/{description}/{price}")
	public void addProduct(String productnumber, String description, double price);
	
	@GetMapping("/product/{productnumber}")
	public ProductDTO getProduct(String productnumber);
	
	@PostMapping("/product/stock/{productnumber}/{quantity}/{locationcode}")
	public void setStock(String productnumber, int quantity, String locationcode);
}
