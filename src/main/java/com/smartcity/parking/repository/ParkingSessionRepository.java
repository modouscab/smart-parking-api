package com.smartcity.parking.repository;

import com.smartcity.parking.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSessionRepository
        extends JpaRepository<ParkingSession, Long> {

}