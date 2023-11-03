package com.example.ridemanagementservice.services;


import com.example.ridemanagementservice.dtos.*;
import com.example.ridemanagementservice.exceptions.NotFoundException;
import com.example.ridemanagementservice.models.Ride;
import com.example.ridemanagementservice.models.RideStatus;
import com.example.ridemanagementservice.repositories.RideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RideManagementService
{
    private RideRepo rideRepo;

    @Autowired
    RideManagementService(RideRepo rideRepo)
    {
        this.rideRepo = rideRepo;
    }

    private int generateAmount()
    {
        Random random = new Random();
        int amount = random.nextInt(100,500);
        return amount;
    }
    public Ride createRideFromRequestDto(CreateRideRequestDto createRideDto,Long userId)
    {
        Ride ride = new Ride();
        ride.setLatitiudeSource(createRideDto.getSourceLatitiude());
        ride.setLongitudeSource(createRideDto.getSourceLongitude());
        ride.setLatitudeDestination(createRideDto.getDestinationLatitude());
        ride.setLongitudeDestination(createRideDto.getDestinationLongitude());
        ride.setUserId(userId);
        ride.setRideStatus(RideStatus.ONGOING);
        ride.setAmount(generateAmount());
        return ride;
    }

    private CreateRideResponseDto convertRideToResponseObject(Ride ride)
    {
        CreateRideResponseDto createRideResponseDto = new CreateRideResponseDto();
        createRideResponseDto.setRideId(ride.getId());
        createRideResponseDto.setHttpStatus(HttpStatus.CREATED);
        return createRideResponseDto;
    }
    public CreateRideResponseDto createRide(CreateRideRequestDto createRideDto,Long userId)
    {
        Ride ride = createRideFromRequestDto(createRideDto,userId);
        Ride savedRide = rideRepo.save(ride);
        return convertRideToResponseObject(savedRide);


    }

    private GetRideResponseDto convertRideToGetRideResponseObject(Ride ride)
    {
         GetRideResponseDto getRideResponseDto = new GetRideResponseDto();
         getRideResponseDto.setSourceLatitiude(ride.getLatitiudeSource());
         getRideResponseDto.setSourceLongitude(ride.getLongitudeSource());
         getRideResponseDto.setDestinationLatitude(ride.getLatitudeDestination());
         getRideResponseDto.setDestinationLongitude(ride.getLongitudeDestination());
         getRideResponseDto.setAmount(ride.getAmount());

         return getRideResponseDto;
    }
    public List<GetRideResponseDto> getAllRides(Long userId)
    {
        List<Ride> allRides = rideRepo.findAllByUserId(userId);
        List<GetRideResponseDto> listOfRides = new ArrayList<>();
        for(Ride ride:allRides)
        {
               listOfRides.add(convertRideToGetRideResponseObject(ride));
        }

        return listOfRides;

    }

    private UpdateRideStatusResponseDto convertUpdateRideStatusToResponseObject(Ride ride)
    {
        UpdateRideStatusResponseDto updateRideStatusResponseDto = new UpdateRideStatusResponseDto();
        updateRideStatusResponseDto.setHttpStatus(HttpStatus.OK);
        updateRideStatusResponseDto.setRideId(ride.getId());
        return updateRideStatusResponseDto;
    }
    public UpdateRideStatusResponseDto updateRideStatus(UpdateRideStatusRequestDto updateRideStatusRequestDto,
                                                        Long rideId)
    {
        Optional<Ride> optionalRide = rideRepo.findById(rideId);
        if(optionalRide.isEmpty() )
        {
            throw new NotFoundException("Ride not Found!");
        }

        Ride ride = optionalRide.get();
        ride.setRideStatus(updateRideStatusRequestDto.getRideStatus());

        Ride updatedRide = rideRepo.save(ride);

        return convertUpdateRideStatusToResponseObject(updatedRide);


    }
}
