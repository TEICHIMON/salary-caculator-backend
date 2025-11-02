# Salary Calculator Backend

Spring Boot + MyBatis + PostgreSQL backend for calculating monthly salary.

## Features

- JWT Authentication (register/login)
- Multiple salary types (1300¥/h, 1500¥/h, or custom)
- Work session management with time period tracking
- Overlap prevention for work sessions
- Monthly and custom period salary summaries

## Requirements

- Docker and Docker Compose
- (Or Java 17+ and Maven if running locally)

## Quick Start with Docker

1. Clone or extract the project
2. Navigate to the project directory
3. Run:

```bash
docker-compose up --build
```

The backend will start on `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login

### Salary Types
- `GET /api/salary-types` - Get all salary types
- `POST /api/salary-types` - Create custom salary type
- `PUT /api/salary-types/{id}` - Update salary type
- `DELETE /api/salary-types/{id}` - Delete salary type

### Work Sessions
- `GET /api/work-sessions?year=2024&month=11` - Get sessions for month
- `GET /api/work-sessions?startDate=2024-11-01&endDate=2024-11-30` - Get sessions for period
- `POST /api/work-sessions` - Create work session
- `PUT /api/work-sessions/{id}` - Update work session
- `DELETE /api/work-sessions/{id}` - Delete work session
- `GET /api/work-sessions/summary?startDate=2024-11-01&endDate=2024-11-30` - Get summary

## Default User

- Username: `admin`
- Password: `admin123`
- Email: `admin@example.com`

## Database

PostgreSQL database is automatically created with:
- Database: `salary_db`
- User: `salary_user`
- Password: `salary_pass`
- Port: `5432`

## Development

To run locally without Docker:

1. Install PostgreSQL and create the database
2. Update `application.yml` with your database credentials
3. Run:

```bash
mvn spring-boot:run
```

## Stopping

```bash
docker-compose down
```

To remove volumes:

```bash
docker-compose down -v
```
