package edu.miu.mwa.lab13;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class FeeCalculatorService {

    private static final String OWNER_TOPIC = "ownertopic";
    
    @KafkaListener(topics = OWNER_TOPIC)
    public void receiveOwner(@Payload TooFastRecord car) 
    {
    	System.out.println("Fee processing: " + car.getLicencePlate()+" " + car.getSpeed());
    }
}
