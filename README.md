# Policy Compliance Testing System

## Project Overview
The Policy Compliance Testing System is a full-stack application developed to manage and evaluate policy compliance.  
It allows users to create, retrieve, update, and delete policy records and check compliance status using REST APIs.

This project was developed as part of a 5-day backend development task using Spring Boot.

## Tech Stack
- Backend: Java, Spring Boot
- Database: H2 / MySQL
- API Testing: Postman
- Version Control: Git & GitHub
- IDE: VS Code
  
## Features
- Create new policy records
- Retrieve all policies
- Filter policies by compliance status
- Update existing policies
- Delete policies
- RESTful API design
- Database integration using JPA

## Day-wise Progress

### Day 1 – Project Setup
- Installed Java, Spring Boot dependencies
- Created Spring Boot project
- Setup project structure
- Configured application.properties
- Verified project running on localhost
  
### Day 2 – Entity & Repository Layer
- Created PolicyCheck entity class
- Added fields:
  - id
  - policyName
  - complianceStatus
  - inputText
  - deleted
- Configured JPA annotations (@Entity, @Id)
- Created PolicyCheckRepository interface
- Extended JpaRepository

### Day 3 – Controller Development (GET & POST)
- Created PolicyCheckController
- Implemented:
  - POST API → Add new policy
  - GET API → Fetch all policies
- Tested APIs using Postman
- Verified data stored in database

### Day 4 – Advanced APIs (Filter, Update, Delete)
- Implemented:
  - GET by compliance status
  - PUT API → Update policy
  - DELETE API → Delete policy
- Handled exceptions
- Improved API responses
- Verified all APIs using Postman

### Day 5 – Testing & Deployment
- Tested all APIs:
  - POST ✔
  - GET ✔
  - PUT ✔
  - DELETE ✔
- Fixed errors and bugs
- Integrated GitHub
- Pushed project to repository
- Created Pull Request

## API Endpoints

### Create Policy
POST /policy-checks

{   "policyName": "Test Policy",   "complianceStatus": "PASS" }

### Get All Policies
GET /policy-checks

### Get by Status
GET /policy-checks/status/{status}

### Update Policy
PUT /policy-checks/{id}

### Delete Policy
DELETE /policy-checks/{id}

## How to Run Project

1. Clone repository
git clone <your-repo-link>

2. Open in VS Code

3. Run Spring Boot Application
BackendApplication.java

4. Access APIs:
http://localhost:8082

## 📸 Output Screenshots
- Postman API responses
- Backend running logs
- GitHub repository

##  Conclusion
This project demonstrates:
- REST API development using Spring Boot
- CRUD operations with database
- Backend architecture design
- Git & GitHub integration

## Future Scope
- Add frontend (React / Angular)
- Implement authentication
- Improve UI/UX
- Add real-time monitoring

## 👩‍💻 Author
Rachna Kallimani  
ECE Student | Java Full Stack Int
