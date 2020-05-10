package edu.miu.mwa.lab12;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	KafkaTemplate<UUID, String> kafkaTemplate;

    private final String ORDER_PREPARED_TOPIC = "ORDER_PREPARED";
    private final String OUT_OF_STOCK_TOPIC = "OUT_OF_STOCK";
    
    private final Random rn = new Random();

    @KafkaListener(topics = "ORDER_BILLED")
    public void receive(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Preparing " + messageid + " " + message);
        
        if (rn.nextInt()%3 == 0) {
        	kafkaTemplate.send(OUT_OF_STOCK_TOPIC, messageid, message);
        }else {
        	kafkaTemplate.send(ORDER_PREPARED_TOPIC, messageid, message);
        }
    }
}