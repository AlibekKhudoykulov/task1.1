package com.example.task1.Repository;

import com.example.task1.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String corpName);
}
