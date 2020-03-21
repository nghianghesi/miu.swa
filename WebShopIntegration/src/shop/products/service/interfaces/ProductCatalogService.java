package shop.products.service.interfaces;

import shop.products.service.ProductDTO;

public interface ProductCatalogService {
	public void addProduct(String productnumber, String description, double price);
	public ProductDTO getProduct(String productnumber);
	public void setStock(String productnumber, int quantity, String locationcode);
}
