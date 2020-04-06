package shop.order.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
@FeignClient("WebShopOrder")
@RibbonClient("WebShopOrder")
public interface OrderService extends shop.order.service.interfaces.OrderService{
}
