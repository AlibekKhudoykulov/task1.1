package com.example.task1.payload;

import com.example.task1.Entity.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull(message = "phoneNumber must not be empty")
    private String phoneNumber;

    @NotNull(message = "address must not be empty")
    private Address address;

    @NotNull(message = "departmentId must not be empty")
    private Integer departmentId;
}
