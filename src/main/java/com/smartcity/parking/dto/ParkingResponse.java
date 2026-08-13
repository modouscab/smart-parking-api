package com.smartcity.parking.dto;

public record ParkingResponse(
        Long id,
        String name,
        int capacity,
        int availableSpots
) {
}