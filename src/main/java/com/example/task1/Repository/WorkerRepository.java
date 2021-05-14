package com.example.task1.Repository;

import com.example.task1.Entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
    boolean existsByPhoneNumber(String phoneNumber);
}
