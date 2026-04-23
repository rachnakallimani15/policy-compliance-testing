package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_checks")
public class PolicyCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;
    private String inputText;
    private String status;

    public PolicyCheck() {}

    public PolicyCheck(String policyName, String inputText, String status) {
        this.policyName = policyName;
        this.inputText = inputText;
        this.status = status;
    }

    public Long getId() { return id; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public String getInputText() { return inputText; }
    public void setInputText(String inputText) { this.inputText = inputText; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}