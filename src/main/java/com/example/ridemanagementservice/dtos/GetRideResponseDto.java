package com.example.ridemanagementservice.dtos;

import com.example.ridemanagementservice.models.Ride;
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

}
