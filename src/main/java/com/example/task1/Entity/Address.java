package com.example.task1.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Street must not be empty")
    private String street;

    @NotNull(message = "HomeNumber must not be empty")
    private String homeNumber;

    public Address(String street, String homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
