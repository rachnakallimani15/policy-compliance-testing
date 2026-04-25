package com.example.backend.controller;

import com.example.backend.entity.PolicyCheck;
import com.example.backend.repository.PolicyCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PolicyCheckController {

    @Autowired
    private PolicyCheckRepository repository;

    // CREATE
    @PostMapping("/save")
    public PolicyCheck savePolicy(@RequestBody PolicyCheck policy) {
        return repository.save(policy);
    }

    // READ ALL
    @GetMapping("/all")
    public List<PolicyCheck> getAll() {
        return repository.findAll();
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deletePolicy(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // UPDATE (IMPORTANT)
    @PutMapping("/update/{id}")
    public PolicyCheck updatePolicy(@PathVariable Long id, @RequestBody PolicyCheck policy) {
        PolicyCheck existing = repository.findById(id).orElseThrow();

        existing.setName(policy.getName());
        existing.setInput(policy.getInput());
        existing.setStatus(policy.getStatus());

        return repository.save(existing);
    }

    // SEARCH
    @GetMapping("/search/{status}")
    public List<PolicyCheck> search(@PathVariable String status) {
        return repository.findByStatus(status);
    }
}