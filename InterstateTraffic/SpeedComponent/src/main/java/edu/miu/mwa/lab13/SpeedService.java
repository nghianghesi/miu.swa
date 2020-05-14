package edu.miu.mwa.lab13;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class SpeedService {

	private Map<String, SensorRecord> trackingCars = new ConcurrentHashMap<>();

	@Autowired
	KafkaTemplate<String, TooFastRecord> kafkaTemplate;

    private final String CAMERA1_TOPIC = "cameratopic1";
    private final String CAMERA2_TOPIC = "cameratopic2";
    
    private final String TOOFAST_TOPIC = "toofasttopic";
    
    @KafkaListener(topics = CAMERA1_TOPIC)
    public void receiveCamera1(@Payload SensorRecord car) 
    {
    	trackingCars.put(car.getLicencePlate(), car);
    }
    
    @KafkaListener(topics = CAMERA2_TOPIC)
    public void receiveCamera2(@Payload SensorRecord car) 
    {
    	SensorRecord previous = trackingCars.remove(car.getLicencePlate());
    	
    	if (previous != null) {
    		int diff = (car.getMinute() - previous.getMinute()) * 60 + car.getSecond() - previous.getSecond();
    		double speed =  0.5 / diff * 3600;
    		
    		System.out.println(car.getLicencePlate()+" " + speed);
    		
    		if (speed > 72) {
    			TooFastRecord toofastEvent = new TooFastRecord(car.getLicencePlate(), speed);
    			this.kafkaTemplate.send(TOOFAST_TOPIC, toofastEvent);
    		}
    	}
    }
}
