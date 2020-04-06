package shop.order.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="api")
@FeignClient(name = "ApiServer", path="/orders")
public interface ApiOrderService extends shop.order.service.interfaces.OrderService{
}
