package com.example.ridemanagementservice.controllers;

import com.example.ridemanagementservice.dtos.HealtCheckupStatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<HealtCheckupStatusDto> healthCheck()
    {
        HealtCheckupStatusDto healtCheckupStatusDto = new HealtCheckupStatusDto();
        try {
            healtCheckupStatusDto.setResponse("System alive");
            return new ResponseEntity<>(
                    healtCheckupStatusDto,
                    HttpStatus.OK
            );
        }catch (Exception e)
        {
            healtCheckupStatusDto.setResponse("System not up");
            return new ResponseEntity<>(
                    healtCheckupStatusDto,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
