package com.example.task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {
    @NotNull(message = "name must not be empty")
    private String name;

    @NotNull(message = "companyId must not be empty")
    private Integer companyId;
}
