# Quiz Application

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
- **Start a new Quiz Session:** Users can initiate a quiz session.
- **Get Random Multiple Choice Questions:** Fetch a question from the database, randomized and non-redundant.
- **Submit Answers:** Users can answer questions and submit them.
- **Retrieve Results:** View the total questions answered along with details on correct and incorrect submissions.

### Additional Features
- **Multiple User Support:** Each user's progress is tracked individually, allowing simultaneous access.
- **Unique Questions Per User Session:** Ensures no repetition of questions within a session.
- **Category-Based Question Fetching:** Filter questions by categories like "Java" or "Python." Defaults to random selection from all categories if none is specified.
- **User Stats Tracking:** View performance stats (e.g., total answered, correct answers) during the session.
- **DTO Usage for Scalability:** Leveraging Data Transfer Objects for improved scalability and performance.

---

## Assumptions

- **H2 Database:** The schema is auto-generated for simplicity.
- **API Usage:** Each API assumes the username is passed as a query parameter.
- **Categories:** Questions are categorized by "Java" and "Python," case-sensitive.
- **Answer Submission:** Option numbers are passed while submitting answers.
- **Non-Sequential Question Fetching:** Users can fetch a new question without answering the previous one.
- **Session Reset:** Starting a new quiz session resets the user's progress.

---

## Prerequisites

- **Java 17+**
- **Maven (for dependencies)**
- **Postman (for API testing)**

---

## Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/heyMadhur/QuizApp-Conceptile-Assignment.git
cd QuizApp-Conceptile-Assignment
```

### Build the Application
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

### Access H2 Database Console (Optional)
- Open [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
- Credentials:
   - **JDBC URL:** jdbc:h2:file:./data/QuizApp
   - **Username:** madhurgupta
   - **Password:** 12345

---

## API Documentation

Base Url: http://localhost:8080/

### 1. Start Quiz
- **Description:** Initiates a quiz session for the user.
- **Endpoint:** `POST /api/quiz/start`
- **Query Parameter:** `username`
- **Sample Response:**
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

### 2. Get Question
- **Description:** Fetches a question for the user, optionally filtered by category.
- **Endpoint:** `GET /api/quiz/get-question`
- **Query Parameters:** `username`, `category` (optional)
- **Sample Response:**
```json
{
    "id": 12,
    "questionTitle": "What is the output of the following Python code snippet? \n\nx = [1, 2, 3]\nx.append(4)\nprint(x)",
    "option1": "[1, 2, 3]",
    "option2": "[1, 2, 3, 4]",
    "option3": "[4, 3, 2, 1]",
    "option4": "Error"
}
```

### 3. Submit Answer
- **Description:** Submits an answer for a given question.
- **Endpoint:** `POST /api/quiz/submit`
- **Query Parameter:** `username`
- **Body:**
```json
{
    "quesId": 12,
    "answer": 2
}
```

### 4. Get Result
- **Description:** Retrieves the user's quiz results.
- **Endpoint:** `GET /api/quiz/get-result`
- **Query Parameter:** `username`

### 5. Get Stats
- **Description:** Provides user performance statistics.
- **Endpoint:** `GET /api/quiz/get-stats`
- **Query Parameter:** `username`

---

## Postman Collection
Import the following collection into Postman to test the APIs:
[Conceptile Assignment QuizApp Postman Collection](https://github.com/heyMadhur/QuizApp-Conceptile-Assignment/blob/main/Conceptile%20Assignment%20QuizApp.postman_collection.json)

---

## Troubleshooting

1. Ensure all dependencies are installed correctly if the application fails to start.
2. Verify the H2 database configuration matches the default settings.
3. Check if the application is running on [localhost:8080](http://localhost:8080).

---

## License
This project is licensed under the MIT License.

---

## Developer
**Madhur Gupta**
- **LinkedIn:** [Madhur Gupta](https://www.linkedin.com/in/madhur-gupta-8a9816221/)
- **Email:** [madhurgupta12112002@gmail.com](mailto:madhurgupta12112002@gmail.com)
