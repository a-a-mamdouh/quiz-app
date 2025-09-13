Quiz App
This project is an interactive web quiz application developed using the Spring Boot framework. The application allows users to create random quizzes, answer questions, and calculate the final scores.

Key Features
RESTful API: The application provides a set of RESTful endpoints for managing questions, creating quizzes, and submitting answers.

Random Quiz Generation: Users can create quizzes based on a specific category and a defined number of questions.

Data Persistence: Uses a PostgreSQL database to permanently store questions and quizzes.

Organized Structure: The code is well-structured into clear layers (Controller, Service, Repository) for easy maintenance and scalability.

Technologies Used
Java 21

Spring Boot 3.x

Spring Data JPA

PostgreSQL

Maven

How to Run
Prerequisites:

Java Development Kit (JDK) 21 or later.

Maven 3.x.

A running PostgreSQL server.

Steps:

Clone the repository:

git clone [your-repository-link-here]


Database Setup:

Make sure your PostgreSQL server is running.

Update the database connection details in the src/main/resources/application.properties file to match your setup.

<!-- end list -->

spring.datasource.url=jdbc:postgresql://localhost:5432/SecurityEX
spring.datasource.username=postgres
spring.datasource.password=1234


Run the Application:

From the project's root directory, execute the following command:
<!-- end list -->

mvn spring-boot:run


The application will run on port 8080.

API Endpoints
You can use Postman or any other testing tool to test the following endpoints:

Question Management
GET /question/allQuestions - Retrieve a list of all questions.

GET /question/{category} - Retrieve questions by category.

POST /question/add - Add a new question.

Quiz Management
POST /quiz/create?category={category}&numQ={number}&title={title} - Create a new quiz.

GET /quiz/get/{id} - Retrieve questions for a specific quiz.

POST /quiz/submit/{id} - Submit quiz answers and calculate the score.
