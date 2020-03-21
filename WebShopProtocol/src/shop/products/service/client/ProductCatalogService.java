package shop.products.service.client;

import shop.products.service.ProductDTO;

@Service
public class ProductCatalogService implements shop.products.service.interfaces.ProductCatalogService{

	@Override
	public void addProduct(String productnumber, String description, double price) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductDTO getProduct(String productnumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStock(String productnumber, int quantity, String locationcode) {
		// TODO Auto-generated method stub
		
	}

}
