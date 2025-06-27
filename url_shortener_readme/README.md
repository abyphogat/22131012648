# URL Shortener Microservice

This is a Spring Boot-based HTTP URL Shortener Microservice for Affordmed Campus Hiring Evaluation.

## Features

- Shorten URLs with optional custom shortcode
- Redirect to original URL using shortcode
- Set custom validity or default to 30 minutes
- Tracks usage statistics (click count, referrer, etc.)
- PostgreSQL as database
- Logging middleware integration for all key actions

## Technologies Used

- Java 17
- Spring Boot 3.2.5
- Maven
- PostgreSQL
- Lombok

## Folder Structure

```
src/
└── main/
    ├── java/
    │   └── com/
    │       └── example/
    │           └── urlShortener/
    │               ├── controller/
    │               ├── dto/
    │               ├── entity/
    │               ├── exception/
    │               ├── middleware/
    │               ├── repository/
    │               ├── service/
    │               └── UrlShortenerApplication.java
    └── resources/
        ├── application.properties
        └── ...
```

## API Endpoints

### 1. Create Short URL

- **POST** `/shorturls`
- Body:
```json
{
  "url": "https://example.com/very/long/link",
  "validity": 30,
  "shortcode": "mycode"
}
```

### 2. Redirect

- **GET** `/{shortcode}`
- Redirects to the original URL.

### 3. Get URL Stats

- **GET** `/shorturls/{shortcode}`
- Returns stats including click count, referrer info, and expiry.

## Logging

Logs are sent to external API using custom middleware.

Sample log payload:

```json
{
  "stack": "backend",
  "level": "error",
  "package": "handler",
  "message": "received string, expected bool"
}
```

© 2025 Afford Medical Technologies Pvt. Ltd.