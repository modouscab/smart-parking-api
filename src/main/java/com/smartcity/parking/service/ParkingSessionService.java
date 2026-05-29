package com.smartcity.parking.service;

import com.smartcity.parking.entity.Parking;
import com.smartcity.parking.entity.ParkingSession;
import com.smartcity.parking.repository.ParkingRepository;
import com.smartcity.parking.repository.ParkingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingSessionService {

    private final ParkingRepository parkingRepository;
    private final ParkingSessionRepository sessionRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public ParkingSession startSession(Long parkingId, String plate) {

        Parking parking = parkingRepository.findByIdForUpdate(parkingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking not found"));

        if (parking.getAvailableSpots() <= 0) {
            throw new RuntimeException("Parking full");
        }

        parking.setAvailableSpots(
                parking.getAvailableSpots() - 1
        );

        ParkingSession session = new ParkingSession();
        session.setParkingId(parkingId);
        session.setCarPlate(plate);
        session.setStartTime(LocalDateTime.now());
        session.setStatus("ACTIVE");

        sessionRepository.save(session);
        parkingRepository.save(parking);

        return session;
    }

    @Transactional
    public ParkingSession endSession(Long sessionId) {

        ParkingSession session = sessionRepository.findById(sessionId)
                .orElseThrow();

        session.setEndTime(LocalDateTime.now());
        session.setStatus("COMPLETED");

        /*long minutes = Duration.between(
                session.getStartTime(),
                session.getEndTime()
        ).toMinutes();

        double price = minutes * 0.05;
        session.setPrice(price);*/

        Parking parking = parkingRepository.findById(session.getParkingId())
                .orElseThrow();

        parking.setAvailableSpots(
                parking.getAvailableSpots() + 1
        );

        sessionRepository.save(session);

        // 🔥 EVENT
        kafkaProducerService.sendSessionEndedEvent(session);


        return session;
    }




}
