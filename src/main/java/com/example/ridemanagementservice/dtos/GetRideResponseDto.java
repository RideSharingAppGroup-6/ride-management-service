package com.example.ridemanagementservice.dtos;

import com.example.ridemanagementservice.models.Ride;
import com.example.ridemanagementservice.models.RideStatus;
import lombok.Data;

import java.util.List;

@Data
public class GetRideResponseDto
{
    Double sourceLatitiude;
    Double sourceLongitude;
    Double destinationLatitude;
    Double destinationLongitude;
    Integer amount;
    RideStatus rideStatus;

}
