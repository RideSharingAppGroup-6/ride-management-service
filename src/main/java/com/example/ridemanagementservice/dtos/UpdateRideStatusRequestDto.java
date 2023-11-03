package com.example.ridemanagementservice.dtos;

import com.example.ridemanagementservice.models.RideStatus;
import lombok.Data;

@Data
public class UpdateRideStatusRequestDto
{
    private RideStatus rideStatus;
}
