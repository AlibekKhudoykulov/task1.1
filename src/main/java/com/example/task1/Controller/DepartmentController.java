package com.example.task1.Controller;

import com.example.task1.Entity.Address;
import com.example.task1.Entity.Department;
import com.example.task1.Service.DepartmentService;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Department one = departmentService.getOne(id);
        return ResponseEntity.status(one == null ? HttpStatus.NO_CONTENT : HttpStatus.FOUND).body(one);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse delete = departmentService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody DepartmentDto departmentDto) {
        ApiResponse add = departmentService.add(departmentDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(add);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        ApiResponse edit = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(edit.isSuccess()?201:209).body(edit);
    }
}
