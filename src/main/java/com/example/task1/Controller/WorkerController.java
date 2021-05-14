package com.example.task1.Controller;

import com.example.task1.Entity.Address;
import com.example.task1.Entity.Worker;
import com.example.task1.Service.WorkerService;
import com.example.task1.payload.ApiResponse;
import com.example.task1.payload.WorkerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Worker one = workerService.getOne(id);
        return ResponseEntity.status(one == null ? HttpStatus.NO_CONTENT : HttpStatus.FOUND).body(one);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse delete = workerService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse add = workerService.add(workerDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(add);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> edit(@Valid @PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse edit = workerService.edit(id, workerDto);
        return ResponseEntity.status(edit.isSuccess()?201:209).body(edit);
    }
}
