package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy-checks")
@CrossOrigin
public class PolicyCheckController {

    @Autowired
    private PolicyCheckRepository policyCheckRepository;

    // CREATE (POST)
    @PostMapping
    public PolicyCheck createPolicy(@RequestBody PolicyCheck policyCheck) {
        return policyCheckRepository.save(policyCheck);
    }

    // GET ALL
    @GetMapping
    public List<PolicyCheck> getAllPolicies() {
        return policyCheckRepository.findAll();
    }

    // GET BY STATUS
    @GetMapping("/status/{status}")
    public List<PolicyCheck> getByStatus(@PathVariable String status) {
        return policyCheckRepository.findByComplianceStatus(status);
    }

    // UPDATE (PUT)
    @PutMapping("/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id, @RequestBody PolicyCheck updatedPolicy) {

        PolicyCheck existing = policyCheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        existing.setPolicyName(updatedPolicy.getPolicyName());
        existing.setComplianceStatus(updatedPolicy.getComplianceStatus());
        existing.setInputText(updatedPolicy.getInputText());

        return policyCheckRepository.save(existing);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deletePolicy(@PathVariable Long id) {
        policyCheckRepository.deleteById(id);
        return "Deleted successfully";
    }
}