package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/policies")
public class PolicyCheckController {

    @Autowired
    private PolicyCheckRepository repository;

    // SAVE
    @PostMapping
    public PolicyCheck savePolicy(@RequestBody PolicyCheck policy) {
        return repository.save(policy);
    }

    // GET ALL
    @GetMapping
    public List<PolicyCheck> getAllPolicies() {
        return repository.findAll();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // 🔥 DAY 7 → SEARCH BY STATUS
    @GetMapping("/status/{status}")
    public List<PolicyCheck> getByStatus(@PathVariable String status) {
        return repository.findByStatus(status);
    }
}