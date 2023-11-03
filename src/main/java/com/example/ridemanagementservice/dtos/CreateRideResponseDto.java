package com.example.ridemanagementservice.dtos;

import com.example.ridemanagementservice.models.Ride;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CreateRideResponseDto
{
    private HttpStatus httpStatus;
    private Long rideId;
}
