package shop.products.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
@FeignClient("WebShopProduct")
@RibbonClient("WebShopProduct")
public interface ProductCatalogService extends shop.products.service.interfaces.ProductCatalogService{
}
