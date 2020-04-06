package shop.customers.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="direct", matchIfMissing = true)
@FeignClient("WebShopCustomer")
@RibbonClient("WebShopCustomer")
public interface CustomerService extends shop.customers.service.interfaces.CustomerService{
}
