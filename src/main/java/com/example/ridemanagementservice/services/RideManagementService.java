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
    public Ride createRideObjectFromRequest(Double sourceLatitude,
                                         Double sourceLongitude,
                                         Double destinationLatitude,
                                         Double destinationLongitude,
                                         Long userId)
    {
        Ride ride = new Ride();
        ride.setLatitiudeSource(sourceLatitude);
        ride.setLongitudeSource(sourceLongitude);
        ride.setLatitudeDestination(destinationLatitude);
        ride.setLongitudeDestination(destinationLongitude);
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
    public CreateRideResponseDto createRide(Double sourceLatitude,
                                            Double sourceLongitude,
                                            Double destinationLatitude,
                                            Double destinationLongitude,
                                            Long userId)
    {
        Ride ride = createRideObjectFromRequest(sourceLatitude,sourceLongitude,destinationLatitude,
                destinationLongitude,userId);
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
         getRideResponseDto.setRideStatus(ride.getRideStatus());

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

    private List<AllRideDetailsResponseDto> convertRideToResponse(List<Ride> rides)
    {
        List<AllRideDetailsResponseDto> allRides = new ArrayList<>();
        for(Ride ride:rides)
        {
            AllRideDetailsResponseDto obj = new AllRideDetailsResponseDto();
            obj.setLatitiudeSource(ride.getLatitiudeSource());
            obj.setLongitudeSource(ride.getLongitudeSource());
            obj.setLatitudeDestination(ride.getLatitudeDestination());
            obj.setLongitudeDestination(ride.getLongitudeDestination());
            obj.setUserId(ride.getUserId());
            obj.setRideStatus(ride.getRideStatus());
            obj.setAmount(ride.getAmount());
            allRides.add(obj);
        }
        return allRides;
    }
    public List<AllRideDetailsResponseDto> getAllRides(List<Long> rideId)
    {
        List<Ride> rides = new ArrayList<>();
        for(Long id:rideId)
        {
            Optional<Ride> optional = rideRepo.findById(id);
            if( optional.isEmpty() )
                throw new NotFoundException("Ride with "+id+" not present");
            Ride ride = optional.get();
            rides.add(ride);
        }
        return convertRideToResponse(rides);
    }

    private UpdateRideStatusResponseDto convertUpdateRideStatusToResponseObject(Ride ride)
    {
        UpdateRideStatusResponseDto updateRideStatusResponseDto = new UpdateRideStatusResponseDto();
        updateRideStatusResponseDto.setHttpStatus(HttpStatus.OK);
        updateRideStatusResponseDto.setRideId(ride.getId());
        return updateRideStatusResponseDto;
    }
    public UpdateRideStatusResponseDto updateRideStatus(RideStatus rideStatus,
                                                        Long rideId)
    {
        Optional<Ride> optionalRide = rideRepo.findById(rideId);
        if(optionalRide.isEmpty() )
        {
            throw new NotFoundException("Ride not Found!");
        }

        Ride ride = optionalRide.get();
        ride.setRideStatus(rideStatus);

        Ride updatedRide = rideRepo.save(ride);

        return convertUpdateRideStatusToResponseObject(updatedRide);


    }
}
