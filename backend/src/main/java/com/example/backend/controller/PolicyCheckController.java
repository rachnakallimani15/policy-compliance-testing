package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/policy-checks")
public class PolicyCheckController {

    private final PolicyCheckRepository repository;

    public PolicyCheckController(PolicyCheckRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    @GetMapping
    public List<PolicyCheck> getAllPolicies() {
        return repository.findAll();
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public PolicyCheck getPolicyById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found with id " + id));
    }

    //  POST
    @PostMapping
    public PolicyCheck createPolicy(@RequestBody PolicyCheck policyCheck) {
        return repository.save(policyCheck);
    }

    //  PUT (UPDATE)
    @PutMapping("/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id, @RequestBody PolicyCheck updatedPolicy) {
        return repository.findById(id).map(policy -> {
            policy.setPolicyName(updatedPolicy.getPolicyName());
            policy.setInputText(updatedPolicy.getInputText());
            policy.setComplianceStatus(updatedPolicy.getComplianceStatus());
            policy.setDeleted(updatedPolicy.isDeleted());
            return repository.save(policy);
        }).orElseThrow(() -> new RuntimeException("Policy not found with id " + id));
    }

    //delete
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        repository.deleteById(id);
    }
}