package com.example.ridemanagementservice.services;

import com.example.ridemanagementservice.dtos.AllRideDetailsResponseDto;
import com.example.ridemanagementservice.dtos.CreateRideResponseDto;
import com.example.ridemanagementservice.dtos.GetRideResponseDto;
import com.example.ridemanagementservice.dtos.UpdateRideStatusResponseDto;
import com.example.ridemanagementservice.models.RideStatus;

import java.util.List;

public interface RideService
{
    public CreateRideResponseDto createRide(Double sourceLatitude,
                                            Double sourceLongitude,
                                            Double destinationLatitude,
                                            Double destinationLongitude,
                                            Long userId);

    public List<GetRideResponseDto> getAllRides(Long userId);
    public List<AllRideDetailsResponseDto> getAllRides(List<Long> rideId);
    public UpdateRideStatusResponseDto updateRideStatus(RideStatus rideStatus,
                                                        Long rideId);
}
