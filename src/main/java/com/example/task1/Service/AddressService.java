package com.example.task1.Service;

import com.example.task1.Entity.Address;
import com.example.task1.Repository.AddressRepository;
import com.example.task1.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAll(){
        return addressRepository.findAll();
    }
    public Address getOne(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        addressRepository.deleteById(id);
        return new ApiResponse("Deleted successfully",true);
    }
    public ApiResponse add(Address address){
        if (addressRepository.existsByHomeNumberAndStreet(address.getHomeNumber(), address.getStreet())) return new ApiResponse("Already exist",false);
        Address address1=new Address(address.getStreet(), address.getHomeNumber());
        addressRepository.save(address1);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,Address address){
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not found",false);
        if (addressRepository.existsByHomeNumberAndStreetAndIdNot(address.getHomeNumber(), address.getStreet(),id)) return new ApiResponse("Already exist",false);
        Address address1 = byId.get();
        address1.setStreet(address.getStreet());
        address1.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address1);
        return new ApiResponse("Saved successfully",true);
    }

}
