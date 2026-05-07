package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PolicyController {

    @Autowired
    private PolicyCheckRepository repository;

    // GET ALL
    @GetMapping("/policies")
    public List<PolicyCheck> getPolicies() {
        return repository.findAll();
    }

    // ADD
    @PostMapping("/policies")
    public PolicyCheck addPolicy(@RequestBody PolicyCheck policy) {
        return repository.save(policy);
    }

    // DELETE
    @DeleteMapping("/policies/{id}")
    public String deletePolicy(@PathVariable Long id) {

        repository.deleteById(id);

        return "Deleted Successfully";
    }

    // UPDATE
    @PutMapping("/policies/{id}")
    public PolicyCheck updatePolicy(
            @PathVariable Long id,
            @RequestBody PolicyCheck updatedPolicy) {

        PolicyCheck policy = repository.findById(id).orElse(null);

        if (policy != null) {

            policy.setInput(updatedPolicy.getInput());
            policy.setName(updatedPolicy.getName());
            policy.setStatus(updatedPolicy.getStatus());

            return repository.save(policy);
        }

        return null;
    }
}