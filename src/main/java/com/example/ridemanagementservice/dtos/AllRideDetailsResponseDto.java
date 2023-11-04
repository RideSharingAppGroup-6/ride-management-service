package com.example.ridemanagementservice.dtos;

import com.example.ridemanagementservice.models.RideStatus;
import lombok.Data;

@Data
public class AllRideDetailsResponseDto
{
    private Double latitiudeSource;
    private Double longitudeSource;
    private Double latitudeDestination;
    private Double longitudeDestination;
    private Long userId;
    private RideStatus rideStatus;
    private Integer amount;
}
