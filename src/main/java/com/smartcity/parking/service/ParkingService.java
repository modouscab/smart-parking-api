package com.smartcity.parking.service;

import com.smartcity.parking.entity.Parking;
import com.smartcity.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public List<Parking> getAllParkings() {
        return repository.findAll();
    }

    public Parking save(Parking parking) {
        return repository.save(parking);
    }

    public Parking getParkingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
    }

    public void deleteParking(Long id) {
        repository.deleteById(id);
    }

}