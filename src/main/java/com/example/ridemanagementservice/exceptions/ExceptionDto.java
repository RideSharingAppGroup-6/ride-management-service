package com.example.ridemanagementservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionDto
{
    private HttpStatus httpStatus;
    private String message;
}
