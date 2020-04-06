package shop.shopping.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import shop.shopping.service.interfaces.CheckoutService;

@ConditionalOnProperty(name = "feignclientsource", havingValue="api")
@FeignClient(name = "ApiServer", path="/shopping")
public interface ApiShoppingService extends shop.shopping.service.interfaces.ShoppingService, CheckoutService{

}
