package com.example.ridemanagementservice.dtos;

import lombok.Data;

@Data
public class CreateRideRequestDto
{
    Double sourceLatitiude;
    Double sourceLongitude;
    Double destinationLatitude;
    Double destinationLongitude;
}
