package edu.miu.mwa.lab13;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private static final String TOOFAST_TOPIC = "toofasttopic";
    private static final String OWNER_TOPIC = "ownertopic";
    
    private static Map<Double, Double> fines = new HashMap<>();
    
    static {
    	fines.put(77.0, 25.0);
    	fines.put(82.0, 45.0);
    	fines.put(90.0, 80.0);
    	fines.put(Double.MAX_VALUE, 125.0);
    };
    
    
    @Autowired
	KafkaTemplate<String, FineRecord> kafkaTemplate;
    
    @KafkaListener(topics = TOOFAST_TOPIC)
    public void receiveToofast(@Payload TooFastRecord car) 
    {
    	if(car.getSpeed() > 72) {
    		System.out.println(car.getLicencePlate()+" " + car.getSpeed());
    		
    		for (Entry<Double, Double> entry : fines.entrySet()) {
    			if (entry.getKey() >= car.getSpeed()) {
    				FineRecord record = new FineRecord(car.getLicencePlate(),car.getSpeed(), entry.getValue());
    				this.kafkaTemplate.send(OWNER_TOPIC, record);
    				break;
    			}
    		}
    	}
    }
}
