# Review Service – REST Microservice

## Overview
ReviewService is an independent Spring Boot microservice responsible for storing, retrieving and managing user reviews submitted for villa bookings.

The main application communicates with this service using Feign Client.

---

## Tech Stack
| Technology | Version |
|------------|---------|
| Java       | 17      |
| Spring Boot| 3.4.0   |
| Database   | MySQL   |
| API Style  | REST JSON |
| Caching    | Spring Cache |
| Scheduling | Cron Jobs |

---

## Domain Entity
### Review
Fields:
- UUID id
- UUID villaId
- UUID reviewerId
- int rating
- String comment
- LocalDateTime createdOn

---

## REST Endpoints
### POST /api/reviews  
➡ creates a new review  

### GET /api/reviews/{villaId}  
➡ returns reviews for a villa  

### DELETE /api/reviews/{id}  
➡ deletes review by ID  

### DELETE /api/reviews/villa/{villaId}  
➡ deletes all reviews for villa  

---

## Functionalities
✔ Maintain list of reviews  
✔ Support average rating calculation (used by main app UI)  
✔ Remove reviews associated with villa deletions  

These functionalities are always **triggered via Feign from the main system**.

---

## Scheduling
Daily statistics job:
prints number of reviews in database

Nightly health check:
reports service status

---

## Caching
Cache name: `reviewsByVilla`

Cache invalidation happens on:
- new review insert
- review deletion
- delete all reviews for a villa

---

## Error Handling
Custom exception:
- `ReviewNotFoundException`

Global error handler intercepts validation and missing resources.

---

## Testing
✔ Unit service tests  
✔ JPA integration test  
✔ API test using MockMvc  

---

## How to Run
Service location: http://http://localhost:9090/health
