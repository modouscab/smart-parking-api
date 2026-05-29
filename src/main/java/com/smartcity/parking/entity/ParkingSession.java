package com.smartcity.parking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parkingId;

    private String carPlate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private Double price;

    private String status;

}