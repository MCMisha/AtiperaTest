# GitHub Repository Fetcher

## Opis

Aplikacja pobiera listę **nie-forkowanych** repozytoriów użytkownika GitHub oraz informacje o każdej gałęzi (`branch`) i ostatnim `SHA` commita.

## Endpoint

GET /api/github/{username}
### Przykład odpowiedzi:
```json
[
  {
    "repositoryName": "DigitalTools",
    "ownerLogin": "MCMisha",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "a1b2c3..."
      }
    ]
  }
]
```
### Błąd (gdy użytkownik nie istnieje):
```json
{
  "status": 404,
  "message": "User not found"
}
```

### Technologie
Java 21

Spring Boot 3.5

RestTemplate

JUnit 5



