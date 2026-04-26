package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PolicyCheckController {

    @Autowired
    private PolicyCheckRepository policyCheckRepository;

    // ✅ SAVE
    @PostMapping("/save")
    public String savePolicy(@RequestBody PolicyCheck policy) {
        policyCheckRepository.save(policy);
        return "Saved Successfully";
    }

    // ✅ GET ALL
    @GetMapping("/getAll")
    public List<PolicyCheck> getAllPolicies() {
        return policyCheckRepository.findAll();
    }

    // ✅ DELETE
    @DeleteMapping("/delete/{id}")
    public String deletePolicy(@PathVariable Long id) {
        policyCheckRepository.deleteById(id);
        return "Deleted Successfully";
    }

    // ✅ UPDATE
    @PutMapping("/update/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id, @RequestBody PolicyCheck updatedPolicy) {
        PolicyCheck policy = policyCheckRepository.findById(id).orElse(null);

        if (policy != null) {
            policy.setName(updatedPolicy.getName());
            policy.setInput(updatedPolicy.getInput());
            policy.setStatus(updatedPolicy.getStatus());
            return policyCheckRepository.save(policy);
        }

        return null;
    }

    // ✅ SEARCH
    @GetMapping("/search/{status}")
    public List<PolicyCheck> searchByStatus(@PathVariable String status) {
        return policyCheckRepository.findByStatus(status);
    }
}