package com.smartcity.parking.controller;

import com.smartcity.parking.entity.Parking;
import com.smartcity.parking.service.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Parking> getAll() {
        return service.getAllParkings();
    }

    @PostMapping
    public Parking create(@RequestBody Parking parking) {
        return service.save(parking);
    }
    @GetMapping("/{id}")
    public Parking getParkingById(@PathVariable Long id) {
        return service.getParkingById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteParking(@PathVariable Long id) {
        service.deleteParking(id);
    }
}