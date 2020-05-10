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
public class DeliveryService {

	@Autowired
	KafkaTemplate<UUID, String> kafkaTemplate;

    private final String ORDER_DELIVERIED_TOPIC = "ORDER_DELIVERIED";
	
    @KafkaListener(topics = "ORDER_PREPARED")
    public void receive(@Payload String message,
    					@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) UUID messageid) 
    {
        System.out.println("Delivering " + messageid + " " + message);
		kafkaTemplate.send(ORDER_DELIVERIED_TOPIC, messageid, message);
    }

}
