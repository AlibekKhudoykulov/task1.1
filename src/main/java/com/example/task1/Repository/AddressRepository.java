package com.example.task1.Repository;

import com.example.task1.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByHomeNumberAndStreet(String homeNumber, String street);
    boolean existsByHomeNumberAndStreetAndIdNot(String homeNumber, String street, Integer id);
}
