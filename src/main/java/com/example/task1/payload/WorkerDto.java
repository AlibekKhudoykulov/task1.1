package com.example.task1.payload;

import com.example.task1.Entity.Address;
import lombok.Data;

@Data
public class WorkerDto {
    private String phoneNumber;
    private Address address;
    private Integer departmentId;
}
