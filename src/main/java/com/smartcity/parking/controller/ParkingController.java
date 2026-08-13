package com.smartcity.parking.controller;

import com.smartcity.parking.dto.ParkingRequest;
import com.smartcity.parking.dto.ParkingResponse;
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
    public List<ParkingResponse> getAll() {
        return service.getAllParkings();
    }

    @PostMapping
    public ParkingResponse create(@RequestBody ParkingRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public ParkingResponse getParkingById(@PathVariable Long id) {
        return service.getParkingById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteParking(@PathVariable Long id) {
        service.deleteParking(id);
    }
}