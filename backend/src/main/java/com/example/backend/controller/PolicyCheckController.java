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

    // GET ALL
    @GetMapping
    public List<PolicyCheck> getAll() {
        return policyCheckRepository.findAll();
    }

    // ADD
    @PostMapping
    public PolicyCheck addPolicy(@RequestBody PolicyCheck policyCheck) {
        return policyCheckRepository.save(policyCheck);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyCheckRepository.deleteById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id, @RequestBody PolicyCheck updatedPolicy) {

        PolicyCheck existing = policyCheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        existing.setPolicyName(updatedPolicy.getPolicyName());
        existing.setInputText(updatedPolicy.getInputText());
        existing.setComplianceStatus(updatedPolicy.getComplianceStatus());

        return policyCheckRepository.save(existing);
    }
}