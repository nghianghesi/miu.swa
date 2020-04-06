package shop.products.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="api")
@FeignClient(name = "ApiServer", path="/products")
public interface ApiProductCatalogService extends shop.products.service.interfaces.ProductCatalogService{
}