package com.example.ridemanagementservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ListOfRideIdRequestDto {
    private List<Long> rideId;
}
