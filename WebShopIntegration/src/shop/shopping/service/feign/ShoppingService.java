package shop.shopping.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import shop.shopping.service.interfaces.CheckoutService;

@ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
@FeignClient("WebShopShopping")
@RibbonClient("WebShopShopping")
public interface ShoppingService extends shop.shopping.service.interfaces.ShoppingService, CheckoutService{

}
