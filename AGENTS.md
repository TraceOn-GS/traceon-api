# AGENTS.md

# TRACEON API

## Overview

TraceOn is a mission-control and space monitoring platform built with:

- Java 21
- Spring Boot
- Domain Driven Design (DDD)
- Maven

The project is structured around Bounded Contexts and follows DDD principles.

The goal is to keep business rules inside the domain and application layers while isolating infrastructure concerns.

---

# Project Structure

Base package:

com.traceon.traceonapi

```
shared/
device/
mission/
telemetry/
alert/
maintenance/
identity/
```

Every bounded context must follow the same structure:

```
<context>/

├── controller
├── application
├── domain
├── dto
└── infrastructure
```

---

# Bounded Contexts

Current and planned contexts:

- device
- mission
- telemetry
- alert
- maintenance
- identity

Each context must remain autonomous.

Cross-context dependencies should be minimized.

Business rules belonging to one context must not be implemented in another context.

---
# Cross-Context References

Bounded Contexts must not duplicate entities from other contexts.

When one context needs to reference an entity owned by another context, it should store only the entity identifier.

Example:

Mission -> Device

Correct:

Set<UUID> dispositivosAssociados

Avoid:

class DispositivoSnapshot {
String nome;
StatusDispositivo status;
...
}

The source context remains responsible for its own data.
Cross-context validations should be performed through application services.
---
# Context Integration Pattern

When a use case requires data from multiple bounded contexts:

Controllers remain unaware of integration details.
Application Services orchestrate interactions.
Domain entities remain isolated.
Repository interfaces stay inside their own context.

Example:

MissionApplicationService

Load Mission
Load Device
Validate Device state
Associate Device to Mission
Persist Mission

Avoid:

Mission entity accessing Device repositories
Controllers orchestrating cross-context logic
Domain entities depending on infrastructure components
Cross-context communication must occur through Application Services.
---
# Layer Responsibilities

## Controller

Responsible only for HTTP concerns.

Allowed:

- Request mapping
- Path variables
- Query parameters
- Request bodies
- Bean Validation
- ResponseEntity
- SuccessResponse

Not allowed:

- Business rules
- Repository access
- Entity manipulation

Flow:

```
HTTP Request
    ↓
Controller
    ↓
Application Service
```

---

## Application

Application Services represent use cases.

Responsible for:

- Orchestrating business flows
- Loading entities
- Calling domain methods
- Persisting changes
- Converting entities into DTOs

Not responsible for:

- HTTP
- Persistence details
- Framework-specific concerns

Flow:

```
Controller
    ↓
Application Service
    ↓
Repository
    ↓
Domain
```

---

## Domain

Contains the business model.

Includes:

- Entities
- Value Objects
- Enums
- Domain Exceptions
- Repository Interfaces
- Domain Services
- Domain Events

The domain layer must not depend on Spring Boot.

Business invariants must be protected here.

Examples:

```java
dispositivo.ativar();

dispositivo.desativar();

dispositivo.colocarEmManutencao();
```

Prefer domain methods over public setters.

Avoid:

```java
dispositivo.setStatus(...)
```

outside the entity.

---

## Infrastructure

Contains technical implementations.

Examples:

- Repository implementations
- External APIs
- Database integrations
- Message brokers

Infrastructure depends on Domain.

Domain must never depend on Infrastructure.

---

# Repository Pattern

Repository Interfaces belong to:

```
domain/repository
```

Repository implementations belong to:

```
infrastructure/repository
```

Services must depend on interfaces.

Example:

```java
private final DispositivoRepositoryInterface repository;
```

Avoid:

```java
private final DispositivoRepository repository;
```

---

# Persistence Strategy

Current implementation:

- In-memory repositories

Future implementation:

- Oracle Database
- Spring Data JPA
- Hibernate

Services and Controllers should remain unchanged when persistence is replaced.

Repositories must abstract persistence details.

---

# Aggregate Roots

Current Aggregate Roots include:

- DispositivoEspacial
- Missao

Planned Aggregate Roots:

- Telemetria
- Alerta
- Manutencao
All modifications inside an aggregate should occur through the aggregate root.

Business consistency must be enforced at the aggregate boundary.

---
# Aggregate Consistency

Aggregate Roots are responsible for protecting all invariants within their boundaries.

Collections owned by an aggregate must only be modified through domain methods.

Correct:

missao.associarDispositivo(id);

missao.removerDispositivo(id);

Avoid:

missao.getDispositivos().add(id);

missao.getDispositivos().remove(id);

Application Services must never manipulate aggregate internals directly.
Aggregate state changes should always occur through domain behaviors.

---
# Collection Guidelines

Prefer Set over List whenever duplicates are not allowed by business rules.

Examples:

Mission -> Associated Devices

Use:

Set<UUID>

instead of:

List<UUID>

when uniqueness is a domain invariant.

Domain methods must enforce collection consistency.
---

# Value Objects

Use Value Objects when identity is not required.

Examples:

- Localizacao
- Coordenada

Guidelines:

- Immutable whenever possible
- Equality based on value
- No independent lifecycle

---

# Domain Services

Use Domain Services when a business rule does not naturally belong to a single entity.

Examples:

- Mission planning
- Orbit calculation
- Telemetry analysis

Avoid placing these rules inside controllers or repositories.

---
# Domain Event Guidelines

Domain Events represent business occurrences that already happened.

Event names should be written in past tense.

Examples:

DispositivoEntrouEmManutencao
MissaoFinalizada
DispositivoAssociadoAMissao
DispositivoRemovidoDaMissao
AlertaGerado

Current project phase:

Event publication may be mocked.
Kafka/RabbitMQ are not required.
Spring Events are optional.
Modeling events is encouraged even when publication is not implemented.

Application Services are responsible for publishing events.
Domain entities are responsible for creating business events.

---

# DTO Conventions

Request DTOs:

```
CreateXRequest
UpdateXRequest
```

Response DTOs:

```
XResponse
```

DTOs should be Java records whenever possible.

Never expose entities directly through the API.

---

# Validation

Validation uses Bean Validation.

Examples:

```java
@NotBlank
@NotNull
@Min
@Max
@Email
```

Controllers must use:

```java
@Valid
```

on request DTOs.

Business validations belong to Domain/Application.

Structural validations belong to DTOs.

Example:

DTO validation:

```text
energiaAtual cannot be null
```

Domain validation:

```text
device in maintenance cannot be activated
```

---
# Cross-Context Validation

Cross-context validations should occur at Application Service level.

Example:

Before associating a device to a mission:

Verify Mission exists.
Verify Device exists.
Verify Device is eligible for association.

The Mission aggregate should not query Device repositories.

The Device aggregate should not query Mission repositories.
Aggregates must remain persistence-agnostic and context-isolated.
---
# API Response Standard

All successful responses must use:

```java
SuccessResponse<T>
```

Structure:

```json
{
  "message": "...",
  "status": 200,
  "data": {}
}
```

---

# Error Response Standard

All errors must use:

```java
ErrorResponse
```

Structure:

```json
{
  "message": "...",
  "status": 404
}
```

---

# Exception Handling

All business exceptions belong to:

```
domain/exception
```

Examples:

- DispositivoNaoEncontradoException
- DispositivoDesativadoException

Every custom exception must have a corresponding handler in:

```
shared/HttpExceptionHandler
```

Controllers must not use try/catch for business exceptions.

Flow:

```
Exception
    ↓
HttpExceptionHandler
    ↓
ErrorResponse
    ↓
HTTP Response
```

---

# Current Device Context Rules

Entity:

```
DispositivoEspacial
```

Status:

```
OPERACIONAL
DESATIVADO
MANUTENCAO
```

Rules:

- New devices start as OPERACIONAL.
- energiaAtual must be between 0 and 100.
- A DESATIVADO device cannot enter maintenance.
- Missing IDs generate DispositivoNaoEncontradoException.

Endpoints:

```
GET    /devices
GET    /devices/{id}
POST   /devices
PUT    /devices/{id}
DELETE /devices/{id}

PATCH  /devices/{id}/ativar
PATCH  /devices/{id}/desativar
PATCH  /devices/{id}/manutencao
```
# Current Mission Context Rules

Entity:

Missao

Status:

PLANEJADA
EM_EXECUCAO
PAUSADA
FINALIZADA
CANCELADA

Rules:

- New missions start as PLANEJADA.
- Only PLANEJADA missions may start.
- Only EM_EXECUCAO missions may pause.
- Only PAUSADA missions may resume.
- FINALIZADA missions cannot be modified.
- CANCELADA missions cannot be resumed.

Mission Device Association Rules:

- Mission stores only Device UUID references.
- Device data remains owned by Device Context.
- A device cannot be associated twice with the same mission.
- Devices may be removed from missions.
- Associations remain even if the device status changes later.
- Device eligibility is validated during association.

Endpoints:
```
GET    /missions
GET    /missions/{id}
POST   /missions
PUT    /missions/{id}
DELETE /missions/{id}

PATCH  /missions/{id}/iniciar
PATCH  /missions/{id}/pausar
PATCH  /missions/{id}/finalizar
PATCH  /missions/{id}/cancelar

GET    /missions/{missionId}/devices
PATCH  /missions/{missionId}/devices/{deviceId}
DELETE /missions/{missionId}/devices/{deviceId}
```
---

# Coding Guidelines

Prefer:

```java
entity.metodoDeDominio();
```

instead of:

```java
entity.setAlgumaCoisa(...);
```

Prefer:

```java
repository.findById(...)
        .orElseThrow(...)
```

over null checks.

Prefer constructor-based dependency injection.

Avoid field injection.

Use Portuguese for:

- API messages
- Exception messages
- Business terminology

Maintain consistency with existing naming conventions.

---

# Future Persistence Guidelines

Current implementation uses in-memory repositories.

Future implementations may use:

Oracle Database
Spring Data JPA
Hibernate

When persistence becomes transactional:

Application Services should define transactional boundaries.
Cross-aggregate updates should be executed within transactions.
Controllers must remain unchanged.

Repository abstractions must prevent persistence technology from leaking into the domain.
---

# Build Commands

Run application:

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Run tests:

Windows:

```powershell
.\mvnw.cmd test
```

Linux/macOS:

```bash
./mvnw test
```

---

# Contributor Rule

Whenever implementing a new bounded context:

1. Follow the same folder structure.
2. Create repository interface in domain.
3. Create repository implementation in infrastructure.
4. Create request/response DTOs.
5. Create application service.
6. Create controller.
7. Create domain exceptions.
8. Register exceptions in HttpExceptionHandler.
9. Follow SuccessResponse/ErrorResponse standards.
10. Preserve DDD boundaries.