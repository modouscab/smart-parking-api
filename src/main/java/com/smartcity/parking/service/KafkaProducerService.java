package com.smartcity.parking.service;

import com.smartcity.parking.entity.ParkingSession;
import com.smartcity.parking.event.ParkingSessionEndedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSessionEndedEvent(ParkingSession session) {

        ParkingSessionEndedEvent event = new ParkingSessionEndedEvent(
                session.getId(),
                session.getParkingId(),
                session.getCarPlate(),
                session.getStartTime(),
                session.getEndTime()
        );

        kafkaTemplate.send("parking-session-ended", event);
    }
}
