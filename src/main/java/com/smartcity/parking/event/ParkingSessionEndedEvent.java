package com.smartcity.parking.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSessionEndedEvent {

    private Long sessionId;
    private Long parkingId;
    private String carPlate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;
}