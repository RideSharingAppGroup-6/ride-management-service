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
        return this.rideManagementService.createRide(createRideRequestDto,userId);


    }

    @GetMapping("{userId}")
    public List<GetRideResponseDto> getRides(@PathVariable("userId") Long userId)
    {
        return rideManagementService.getAllRides(userId);
    }


    @PutMapping(value = {"{rideId}", "/cancel/{rideId}"})
    public UpdateRideStatusResponseDto updateRideStatus(@RequestBody UpdateRideStatusRequestDto updateRideStatusRequestDto,
                                                        @PathVariable("rideId") Long rideId)
    {
        return rideManagementService.updateRideStatus(updateRideStatusRequestDto,rideId);
    }








}
