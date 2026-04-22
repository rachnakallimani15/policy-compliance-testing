package com.example.backend.repository;

import com.example.backend.entity.PolicyCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyCheckRepository extends JpaRepository<PolicyCheck, Long> {
}