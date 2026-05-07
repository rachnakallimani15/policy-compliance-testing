package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.PolicyCheck;

public interface PolicyCheckRepository
        extends JpaRepository<PolicyCheck, Long> {

    List<PolicyCheck> findByStatus(String status);

}