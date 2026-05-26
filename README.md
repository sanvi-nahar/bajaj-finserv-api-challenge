# Bajaj Finserv Health API Challenge

## API Endpoint

### POST /bfhl

**Request:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "sanvi_nahar_31082004",
  "email": "sanvinahar230695@acropolis.in",
  "roll_number": "0827CI231117",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET /bfhl

Returns: `{"operation_code": 1}`

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Maven
- Lombok

## Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

## Author

- **Name:** Sanvi Nahar
- **Email:** sanvinahar230695@acropolis.in
- **Roll Number:** 0827CI231117
