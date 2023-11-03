package com.example.ridemanagementservice.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UpdateRideStatusResponseDto {
    private HttpStatus httpStatus;
    private Long rideId;
}
