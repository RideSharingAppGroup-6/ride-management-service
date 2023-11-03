package com.example.ridemanagementservice.repositories;

import com.example.ridemanagementservice.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepo extends JpaRepository<Ride,Long> {



    Ride save(Ride ride);


    List<Ride> findAllByUserId(Long userId);

    @Override
    Optional<Ride> findById(Long rideId);
}
