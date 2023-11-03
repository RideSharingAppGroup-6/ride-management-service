package com.example.ridemanagementservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseModel
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


}
