package edu.miu.mwa.lab12;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	KafkaTemplate<UUID, String> kafkaTemplate;

    private final String ORDER_CREATED_TOPIC = "ORDER_CREATED";
    
	public UUID placeOrder(String message) {
		UUID messageid = UUID.randomUUID();
		System.out.println("Processing Order " + message);
		kafkaTemplate.send(ORDER_CREATED_TOPIC, messageid, message);
		return messageid;
	}
	
    @KafkaListener(topics = "OUT_OF_STOCK_TOPIC")
    public void outofstock(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Out of stock, canceling order " + messageid + " " + message);
    }
	
    @KafkaListener(topics = "ORDER_DELIVERIED")
    public void deliveried(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Order proceeded " + messageid + " " + message);
    }
}
