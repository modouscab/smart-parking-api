package com.smartcity.parking.service;

import com.smartcity.parking.dto.ParkingRequest;
import com.smartcity.parking.dto.ParkingResponse;
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

    public List<ParkingResponse> getAllParkings() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ParkingResponse save(ParkingRequest request) {

        Parking parking = new Parking();

        parking.setName(request.name());
        parking.setCapacity(request.capacity());
        parking.setAvailableSpots(request.availableSpots());

        Parking saved = repository.save(parking);

        return toResponse(saved);
    }

    public ParkingResponse getParkingById(Long id) {

        Parking parking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));

        return toResponse(parking);
    }

    public void deleteParking(Long id) {
        repository.deleteById(id);
    }

    private ParkingResponse toResponse(Parking parking) {
        return new ParkingResponse(
                parking.getId(),
                parking.getName(),
                parking.getCapacity(),
                parking.getAvailableSpots()
        );
    }
}