package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyCheckRepository repository;

    @GetMapping
    public List<PolicyCheck> getAllPolicies() {
        return repository.findAll();
    }

    @PostMapping
    public PolicyCheck addPolicy(@RequestBody PolicyCheck policy) {
        return repository.save(policy);
    }

    @PutMapping("/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id,
                                    @RequestBody PolicyCheck updatedPolicy) {

        PolicyCheck policy = repository.findById(id).orElseThrow();

        policy.setInput(updatedPolicy.getInput());
        policy.setName(updatedPolicy.getName());
        policy.setStatus(updatedPolicy.getStatus());

        return repository.save(policy);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        repository.deleteById(id);
    }
}