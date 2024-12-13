# Quiz App

## Description
A simple Quiz Application built using Spring Boot with an H2 Database for data persistence. The app allows users to:

- Start a quiz.
- Fetch questions based on categories.
- Submit answers.
- Retrieve the quiz results.

The app is seeded with sample questions and a default user for testing purposes.

---

## Features
### Key Features
- Start a new Quiz Session.
- Get a random multiple choice question from a set of questions in database
- Submit answer
- Get the total questions answered by user with details on correct and incorrect submission.


### Additional Features
1. **Multiple User Support:**  
   The application supports multiple users accessing the quiz app simultaneously. Each user's progress is tracked individually.

2. **Unique Questions Per User Session:**  
   Questions answered by the user in a session are not repeated, ensuring a seamless and non-redundant experience.

3. **Category-Based Question Fetching:**  
   Users can filter questions based on specific categories (e.g., Java, Python). If no category is specified, questions are fetched randomly from all available categories.

4. **Dynamic Reset of Progress:**  
   Starting a new quiz session automatically resets the user's progress, ensuring a fresh start each time.
5. **User Stats Tracking:**

   Users can view their performance stats (total questions answered, correct answers, incorrect answers) at any point during the session.

6. **DTO Usage for Scalability:**

   The application utilizes Data Transfer Objects (DTOs) to enhance future scalability and maintain high performance.


---

## Assumptions
1. The H2 Database is used for simplicity, and the schema is automatically created based on the application.
2. A default user (`Madhur`) is pre-seeded in the database while Testing. You can create new User while starting the quiz.
3. The application assumes that each API is tested using the `username` provided in the query parameters.
4. Questions are categorized by "Java" and "Python" in database with Case Sensitivity. 
5. If no category is passed while Fetching the Question, it will fetch from both Category.
6. While submitting the answer, it is assumed that option number will be passed instead of any other value.
7. Everytime the User starts a new Quiz, his previous data will be reset.

---

## Prerequisites
1. Java 17+
2. Maven or Gradle (for dependencies)
3. Postman (for API testing)

---

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. **Build the Application:**
   ```bash
   mvn clean install
   ```

3. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access H2 Database Console (Optional):**
   Open `http://localhost:8080/h2-console` in your browser. Use the following credentials:
    - JDBC URL: `jdbc:h2:file:./data/QuizApp`
    - Username: `madhurgupta`
    - Password: 12345

5. **Test APIs using Postman:**
   Use the Postman collection provided below to test the APIs.

---

## API Documentation

### 1. Start Quiz
**Endpoint:** `POST /api/quiz/start?username={username}`

**Description:** Starts a quiz for the given user.

**Example Request:**
```bash
POST http://localhost:8080/api/quiz/start?username=Madhur
```

**Example Response:**
```json
{
  "message": "Welcome Back Madhur",
  "user": {
    "id": 1,
    "username": "Madhur",
    "totalQuestionsAnswered": 0,
    "correctAnswers": 0,
    "incorrectAnswers": 0
  }
}
```

---

### 2. Get Question
**Endpoint:** `GET /api/quiz/get-question?username={username}&category={category}`

**Description:** Fetches a question from the given category.

**Example Request:**
```bash
GET http://localhost:8080/api/quiz/get-question?username=Madhur&category=Python
```

**Example Response:**
```json
{
  "id": 16,
  "questionTitle": "Which Python module is used for working with dates and times?",
  "option1": "datetime",
  "option2": "math",
  "option3": "os",
  "option4": "sys"
}
```

---

### 3. Submit Answer
**Endpoint:** `POST /api/quiz/submit?username={username}`

**Description:** Submits an answer for the given question.

**Example Request:**
```bash
POST http://localhost:8080/api/quiz/submit?username=Madhur
Content-Type: application/json
{
  "quesId": 16,
  "answer": 1
}
```

**Example Response:**
```json
{
  "message": "Answer Submitted Successfully",
  "totalQuestionAnswered": 1
}
```

---

### 4. Get Result
**Endpoint:** `GET /api/quiz/get-result?username={username}`

**Description:** Retrieves the quiz result for the given user.

**Example Request:**
```bash
GET http://localhost:8080/api/quiz/get-result?username=Madhur
```

**Example Response:**
```json
{
  "username": "Madhur",
  "totalQuestionsAnswered": 10,
  "correctAnswers": 8,
  "incorrectAnswers": 2
}
```

---

## Postman Collection
Import the following collection into Postman to test the APIs:

```json
{
  "info": {
    "_postman_id": "be127b8d-372b-453e-a950-bf76f9bd6903",
    "name": "Conceptile Assignment QuizApp",
    "schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json"
  },
  "item": [ ... ]
}
```

---

## Troubleshooting

1. If the application fails to start, ensure all dependencies are installed properly.
2. Verify the H2 database configuration matches the default settings.
3. Check if the application is running on `localhost:8080`.

---

## License
This project is licensed under the MIT License.
