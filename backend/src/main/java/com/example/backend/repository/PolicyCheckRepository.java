package com.example.backend.repository;

import com.example.backend.entity.PolicyCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PolicyCheckRepository extends JpaRepository<PolicyCheck, Long> {

    List<PolicyCheck> findByStatus(String status);
}