package com.example.ridemanagementservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Ride extends BaseModel
{
    private Double latitiudeSource;
    private Double longitudeSource;
    private Double latitudeDestination;
    private Double longitudeDestination;
    private Long userId;
    private RideStatus rideStatus;
    private Integer amount;

}
