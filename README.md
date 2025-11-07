# 🗒️ Notes App — Spring Boot + MongoDB

A simple Spring Boot application for storing daily notes with **tags**, **pagination**, **filtering**, and **word statistics per note**.

---

## 🚀 Technologies
- **Java 21**
- **Spring Boot 3**
- **MongoDB**
- **Docker & Docker Compose**
- **Lombok**
- **Spring Data MongoDB**

---

## 📦 How to Run

### 🔹 Option 1: Run with Docker Compose
Clone the repository and navigate to the project directory:
```bash
git clone https://github.com/yourusername/notes-app.git
cd notes-app
```

Then start the containers:
```bash
docker-compose up --build
```

After startup:
- API will be available at [http://localhost:8080](http://localhost:8080)
- MongoDB will be available on port **27017**

---

### 🔹 Option 2: Run locally (without Docker)
1. Run **MongoDB** locally on port `27017`.
2. In your `application.properties`, make sure you have:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/notesdb
   ```
3. Start the app:
   ```bash
   mvn spring-boot:run
   ```

---

## 🧩 API Endpoints

### ➕ Create a Note
**POST** `/notes`
```json
{
  "title": "My first note",
  "text": "This is a test note for my app",
  "tag": "BUSINESS"
}
```

📤 **Response:**
```json
200 OK
```

---

### 📄 Get a List of Notes
**GET** `/notes/all?page=0&size=5&sortDirection=DESC&tags=BUSINESS&tags=PERSONAL`

📤 **Response Example:**
```json
{
  "content": [
    {
      "id": "671efb4f512d918c1dbdfa80",
      "title": "My first note",
      "dateCreation": "2025-11-07T12:00:12.217"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": false,
      "empty": true,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 2,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "empty": true,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 2,
  "empty": false
}
```

---

### 📘 Get a Single Note
**GET** `/notes/{id}`

📤 **Response:**
```json
{
  "id": "671efb4f512d918c1dbdfa80",
  "title": "My first note",
  "text": "This is a test note for my app",
  "tag": "BUSINESS",
  "dateCreation": "2025-11-07T12:00:12.217"
}
```

---

### ✏️ Update a Note
**PUT** `/notes/{id}`
```json
{
  "title": "Updated title",
  "text": "Updated text",
  "tag": "IMPORTANT"
}
```

---

### ❌ Delete a Note
**DELETE** `/notes/{id}`

📤 **Response:**
```
204 No Content
```

---

### 📊 Get Note Statistics
**GET** `/notes/{id}/stats`

📤 **Response:**
```json
{
  "id": "671efb4f512d918c1dbdfa80",
  "counts": {
    "note": 2,
    "is": 1,
    "just": 1,
    "a": 1
  }
}
```

---

## 🧪 Test Cases

| # | Test                                         | Expected Result |
|---|----------------------------------------------|-----------------|
| 1 | POST `/notes` without `title`                | 400 Bad Request |
| 2 | POST `/notes` with invalid tag `RANDOM`      | 400 Bad Request |
| 3 | GET `/notes/all` with `sortDirection=ASC`    | Returns oldest notes first |
| 4 | GET `/notes/all?tags=BUSINESS&tags=PERSONAL` | Filters notes by these tags |
| 5 | GET `/notes/{id}/stats`                      | Returns a map of unique words and their frequencies |

---

## ⚙️ Environment Variables (Docker)

| Name | Description | Example |
|------|--------------|----------|
| `SPRING_DATA_MONGODB_URI` | Connection URI for MongoDB | `mongodb://root:password@mongodb:27017/notesdb?authSource=admin` |
