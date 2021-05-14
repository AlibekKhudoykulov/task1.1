package com.example.task1.Service;

import com.example.task1.Entity.Address;
import com.example.task1.Entity.Company;
import com.example.task1.Repository.AddressRepository;
import com.example.task1.Repository.CompanyRepository;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    public Company getOne(Integer id){
        Optional<Company> byId =companyRepository.findById(id);
        if (!byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted successfully",true);
    }
    public ApiResponse add(Company company){
        if (companyRepository.existsByCorpName(company.getCorpName())) return new ApiResponse("Already exist",false);
        if (addressRepository.existsByHomeNumberAndStreet(company.getAddress().getHomeNumber(),company.getAddress().getStreet()))
            return new ApiResponse("Address already exist",false);
        Address address=new Address(company.getAddress().getStreet(),company.getAddress().getHomeNumber());
        addressRepository.save(address);
        Company company1=new Company();
        company1.setAddress(address);
        company1.setCorpName(company.getCorpName());
        company1.setDirectorName(company.getDirectorName());
        companyRepository.save(company1);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,Company company){
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not found",false);
        if (addressRepository.existsByHomeNumberAndStreetAndIdNot(company.getAddress().getHomeNumber(), company.getAddress().getStreet(),id)) return new ApiResponse("Already exist",false);
        Company company1 = byId.get();
        company1.setAddress(company.getAddress());
        company1.setDirectorName(company.getDirectorName());
        company1.setCorpName(company.getCorpName());
        companyRepository.save(company1);
        return new ApiResponse("Saved successfully",true);
    }
}
