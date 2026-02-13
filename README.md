# 📅 Schedule App (Spring Boot)

세션 기반 로그인 방식의 간단한 일정 관리 애플리케이션입니다.

---
## 👤 사용자(User)

### 회원가입

**POST** `/users/signup`

#### Request Body
```json
{
  "username": "ljh",
  "password": "pw12345678"
}
```
Response
- 201 Created

---
로그인
Request Body
```json
{
  "username": "ljh",
  "password": "pw12345678"
}
```
Response
- 200 OK

로그인 성공 시 세션에 loginUser 저장

실패 시 401 Unauthorized

---

📆 일정(Schedule)

모든 일정 API는 로그인 상태(세션 필수)에서만 동작
---
일정 생성

POST /schedules

Request Body
```json
{
  "title": "string",
  "content": "string",
  "password": "pw12345678"
}
```
Response
- 201 Created
---
일정 전체 조회

GET /schedules

Response
```json
  {
    "id": 1,
    "title": "string",
    "content": "string",
    "username": "ljh"
  }
```
---
일정 단건 조회

GET /schedules/{id}

Response
```json
{
  "id": 1,
  "title": "string",
  "content": "string",
  "username": "ljh"
}
```
---
일정 수정

PUT /schedules/{id}

Request Body

```json
{
  "title": "string",
  "content": "string",
  "password": "pw12345678"
}
```
Response
- 200 OK
---
일정 삭제

DELETE /schedules/{id}

Request Body
```json
{
  "password": "pw12345678"
}
```
Response
- 204 No Content




## 🗂 ERD

### USER

| 컬럼명 | 타입 | 제약조건 |
|------|------|----------|
| id | BIGINT | PK |
| username | VARCHAR | UNIQUE |
| password | VARCHAR | NOT NULL |

---

### SCHEDULE

| 컬럼명 | 타입 | 제약조건 |
|--------|--------|----------------|
| id | BIGINT | PK |
| title | VARCHAR | NOT NULL |
| content | VARCHAR | NOT NULL |
| password | VARCHAR | NOT NULL |
| user_id | BIGINT | FK → USER(id) |
