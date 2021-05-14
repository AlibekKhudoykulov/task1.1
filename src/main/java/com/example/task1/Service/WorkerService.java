package com.example.task1.Service;

import com.example.task1.Entity.Address;
import com.example.task1.Entity.Department;
import com.example.task1.Entity.Worker;
import com.example.task1.Repository.AddressRepository;
import com.example.task1.Repository.DepartmentRepository;
import com.example.task1.Repository.WorkerRepository;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import com.example.task1.payload.WorkerDto;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getAll(){
        return workerRepository.findAll();
    }
    public Worker getOne(Integer id){
        Optional<Worker> byId =workerRepository.findById(id);
        if (!byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        workerRepository.deleteById(id);
        return new ApiResponse("Deleted successfully",true);
    }
    public ApiResponse add(WorkerDto workerDto){
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())) return new ApiResponse("Already exist",false);
        if (addressRepository.existsByHomeNumberAndStreet(workerDto.getAddress().getHomeNumber(),workerDto.getAddress().getStreet()))
            return new ApiResponse("Address already exist",false);
        Address address=new Address(workerDto.getAddress().getStreet(),workerDto.getAddress().getHomeNumber());
        addressRepository.save(address);
        Worker worker=new Worker();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(departmentRepository.getOne(workerDto.getDepartmentId()));
        workerRepository.save(worker);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,WorkerDto workerDto){
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not found",false);
        if (workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id)) return new ApiResponse("Already exist",false);
        Worker worker=new Worker();
        worker.setPhoneNumber(worker.getPhoneNumber());
        worker.setAddress(workerDto.getAddress());
        worker.setDepartment(departmentRepository.getOne(workerDto.getDepartmentId()));
        workerRepository.save(worker);
        return new ApiResponse("Saved successfully",true);
    }
}
