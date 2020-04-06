package shop.customers.service.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "feignclientsource", havingValue="api")
@FeignClient(name = "ApiServer", path="/customers")
public interface ApiCustomerService extends shop.customers.service.interfaces.CustomerService{
}
