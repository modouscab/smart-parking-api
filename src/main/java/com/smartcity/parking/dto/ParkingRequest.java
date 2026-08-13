package com.smartcity.parking.dto;

public record ParkingRequest(
        String name,
        int capacity,
        int availableSpots
) {
}