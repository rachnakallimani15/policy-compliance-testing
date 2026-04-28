package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_check")
public class PolicyCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String input;
    private String status;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInput() {
        return input;
    }

    public String getStatus() {
        return status;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}