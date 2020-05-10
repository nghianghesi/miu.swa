package edu.miu.mwa.lab12;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
public class PaymentService {

	@Autowired
	KafkaTemplate<UUID, String> kafkaTemplate;

    private final String ORDER_BILLED_TOPIC = "ORDER_BILLED";
	
    @KafkaListener(topics = "ORDER_CREATED")
    public void receive(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Processing payment " + messageid + " " + message);
		kafkaTemplate.send(ORDER_BILLED_TOPIC, messageid, message);
    }
	
    @KafkaListener(topics = "OUT_OF_STOCK_TOPIC")
    public void outofstock(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Out of stock, canceling payment " + messageid + " " + message);
    }
}
