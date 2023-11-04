package com.example.ridemanagementservice.controllers;


import com.example.ridemanagementservice.dtos.*;
import com.example.ridemanagementservice.services.RideManagementService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rides")
public class RideManagementController
{
    private RideManagementService rideManagementService;

    @Autowired
    public RideManagementController(RideManagementService rideManagementService)
    {
        this.rideManagementService = rideManagementService;
    }

    @PostMapping("{userId}")
    public CreateRideResponseDto createRide(@RequestBody CreateRideRequestDto createRideRequestDto,
                                            @PathVariable("userId") Long userId)
    {
        return this.rideManagementService.createRide(createRideRequestDto.getSourceLatitiude(),
                createRideRequestDto.getSourceLongitude(), createRideRequestDto.getDestinationLatitude(),
                createRideRequestDto.getDestinationLongitude(),
                userId);


    }

    @GetMapping("{userId}")
    public List<GetRideResponseDto> getAllRides(@PathVariable("userId") Long userId)
    {
        return rideManagementService.getAllRides(userId);
    }

    @GetMapping
    public List<AllRideDetailsResponseDto> getAllRides(@RequestBody ListOfRideIdRequestDto listOfRideIdRequestDto)
    {
            return rideManagementService.getAllRides(listOfRideIdRequestDto.getRideId());
    }


    @PutMapping("{rideId}" )
    public UpdateRideStatusResponseDto updateRideStatus(@RequestBody UpdateRideStatusRequestDto updateRideStatusRequestDto,
                                                        @PathVariable("rideId") Long rideId)
    {
        return rideManagementService.updateRideStatus(updateRideStatusRequestDto.getRideStatus(),rideId);
    }








}
