package com.smartcity.parking.controller;

import com.smartcity.parking.entity.ParkingSession;
import com.smartcity.parking.service.ParkingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class ParkingSessionController {

    private final ParkingSessionService service;

    @PostMapping("/start")
    public ParkingSession start(@RequestParam Long parkingId,
                                @RequestParam String plate) {

        return service.startSession(parkingId, plate);
    }

    @PostMapping("/end")
    public ParkingSession end(@RequestParam Long sessionId) {
        return service.endSession(sessionId);
    }
}