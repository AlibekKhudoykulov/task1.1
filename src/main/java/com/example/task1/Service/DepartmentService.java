package com.example.task1.Service;

import com.example.task1.Entity.Address;
import com.example.task1.Entity.Company;
import com.example.task1.Entity.Department;
import com.example.task1.Repository.CompanyRepository;
import com.example.task1.Repository.DepartmentRepository;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
    public Department getOne(Integer id){
        Optional<Department> byId =departmentRepository.findById(id);
        if (!byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        departmentRepository.deleteById(id);
        return new ApiResponse("Deleted successfully",true);
    }
    public ApiResponse add(DepartmentDto departmentDto){
        if (departmentRepository.existsByNameAndCompanyId(departmentDto.getName(),departmentDto.getCompanyId())) return new ApiResponse("Already exist",false);
        Department department=new Department();
        department.setCompany(companyRepository.getOne(departmentDto.getCompanyId()));
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,DepartmentDto departmentDto){
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not found",false);
        if (departmentRepository.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(),id)) return new ApiResponse("Already exist",false);
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(companyRepository.getOne(departmentDto.getCompanyId()));
        departmentRepository.save(department);
        return new ApiResponse("Saved successfully",true);
    }
}
