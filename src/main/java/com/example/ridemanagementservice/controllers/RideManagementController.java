package com.example.ridemanagementservice.controllers;


import com.example.ridemanagementservice.dtos.*;
import com.example.ridemanagementservice.services.RideManagementService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CreateRideResponseDto> createRide(@RequestBody CreateRideRequestDto createRideRequestDto,
                                                           @PathVariable("userId") Long userId)
    {

        CreateRideResponseDto response =  this.rideManagementService.createRide(createRideRequestDto.getSourceLatitiude(),
                createRideRequestDto.getSourceLongitude(), createRideRequestDto.getDestinationLatitude(),
                createRideRequestDto.getDestinationLongitude(),
                userId);

        if( response==null )
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );


    }

    @GetMapping("{userId}")
    public ResponseEntity<List<GetRideResponseDto>> getAllRides(@PathVariable("userId") Long userId)
    {
        List<GetRideResponseDto> allRides = rideManagementService.getAllRides(userId);
        return new ResponseEntity<>(allRides,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AllRideDetailsResponseDto>> getAllRides(@RequestBody ListOfRideIdRequestDto listOfRideIdRequestDto)
    {
           List<AllRideDetailsResponseDto> response = rideManagementService.getAllRides(listOfRideIdRequestDto.getRideId());
           return new ResponseEntity<>(
                   response,
                   HttpStatus.OK
           );
    }


    @PutMapping("{rideId}" )
    public ResponseEntity<UpdateRideStatusResponseDto> updateRideStatus(@RequestBody UpdateRideStatusRequestDto updateRideStatusRequestDto,
                                                        @PathVariable("rideId") Long rideId)
    {
        return new ResponseEntity<>(
                rideManagementService.updateRideStatus(updateRideStatusRequestDto.getRideStatus(),rideId),
                HttpStatus.OK);
    }








}
