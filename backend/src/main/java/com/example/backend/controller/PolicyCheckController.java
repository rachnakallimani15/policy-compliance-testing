package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy-checks")
@CrossOrigin(origins = "*")
public class PolicyCheckController {

    @Autowired
    private PolicyCheckRepository repository;

    // GET ALL
    @GetMapping
    public List<PolicyCheck> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PolicyCheck getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE
    @PostMapping
    public PolicyCheck create(@RequestBody PolicyCheck policyCheck) {
        return repository.save(policyCheck);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PolicyCheck update(@PathVariable Long id, @RequestBody PolicyCheck policyCheck) {
        PolicyCheck existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setPolicyName(policyCheck.getPolicyName());
            existing.setComplianceStatus(policyCheck.getComplianceStatus());
            return repository.save(existing);
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // SEARCH BY STATUS (NEW FEATURE)
    @GetMapping("/status/{status}")
    public List<PolicyCheck> getByStatus(@PathVariable String status) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getComplianceStatus().equalsIgnoreCase(status))
                .toList();
    }
}