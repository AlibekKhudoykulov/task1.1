package com.example.task1.payload;

import com.example.task1.Entity.Address;
import lombok.Data;

@Data
public class CompanyDto {
    private String corpName;
    private String directorName;
    private Address address;
}
