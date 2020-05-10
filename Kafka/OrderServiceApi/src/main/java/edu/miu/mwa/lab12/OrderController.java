package edu.miu.mwa.lab12;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
    
	@GetMapping("/order/place/{message}")
	public String placeOrder(@PathVariable String message) {
		this.orderService.placeOrder(message);
		return "Order Placed";
	}
}
