
# RentRead API

This API provides endpoints for managing books, users, and rental operations for a book rental service.

## Tech Stack

- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **Spring Security**: For authentication and authorization
- **Gradle**: Build automation tool
- **MySQL**: Database management system
- **Hibernate**: ORM (Object-Relational Mapping) framework

## Setup

To set up and run the RentRead API locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/Rajatsinha05/RentRead.git
   ```
2. Navigate to the project directory:
   ```bash
   cd RentRead
   ```
3. Build the project using Gradle:
   ```bash
   gradle clean build
   ```
4. Run
   ```java
   ./gradlew bootRun
   ```


### User Management

#### Register User
- **Endpoint:** `POST /user/register`
- **Description:** Registers a new user in the system.
- **Request Body:**
  ```json
  {
    "firstName": "Rajat",
    "lastName": "Sinha",
    "email": "rajat.sinha@example.com",
    "password": "password123",
    "role": "USER"
  }
  ```
- **Response:**
  - Status: 201 Created
  - Body:
    ```json
    {
      "id": 1,
      "firstName": "Rajat",
      "lastName": "Sinha",
      "email": "rajat.sinha@example.com",
      "role": "USER",
      "books": []
    }
    ```

#### User Login
- **Endpoint:** `POST /user/login`
- **Description:** Authenticates a user and returns an access token.
- **Request Body:**
  ```json
  {
    "email": "rajat.sinha@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    ```

### Book Management

#### Create Book
- **Endpoint:** `POST /books`
- **Description:** Adds a new book to the system.
- **Request Body:**
  ```json
  {
    "title": "Sample Book",
    "author": "Jane Smith",
    "genre": "Thriller",
    "available": true
  }
  ```
- **Response:**
  - Status: 201 Created
  - Body:
    ```json
    {
      "id": 1,
      "title": "Sample Book",
      "author": "Jane Smith",
      "genre": "Thriller",
      "available": true
    }
    ```

#### Get All Books
- **Endpoint:** `GET /books`
- **Description:** Retrieves all books in the system.
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    [
      {
        "id": 1,
        "title": "Sample Book",
        "author": "Jane Smith",
        "genre": "Thriller",
        "available": true
      },
      {
        "id": 2,
        "title": "Another Book",
        "author": "John Doe",
        "genre": "Mystery",
        "available": false
      }
    ]
    ```

#### Update Book
- **Endpoint:** `PUT /books/{id}`
- **Description:** Updates information about a specific book.
- **Request Body:**
  ```json
  {
    "title": "Updated Book Title"
  }
  ```
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "id": 1,
      "title": "Updated Book Title",
      "author": "Jane Smith",
      "genre": "Thriller",
      "available": true
    }
    ```

#### Delete Book
- **Endpoint:** `DELETE /books/{id}`
- **Description:** Deletes a book from the system.
- **Response:**
  - Status: 204 No Content

### Rental Management

#### Rent Book
- **Endpoint:** `POST /books/rent/{id}`
- **Description:** Allows a user to rent a book.
- **Request Body:**
  ```json
  {
    "userId": 123
  }
  ```
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "firstName": "Rajat",
      "lastName": "Sinha",
      "email": "rajat.sinha@example.com",
      "books": [
        {
          "id": 1,
          "title": "Sample Book",
          "author": "Jane Smith",
          "genre": "Thriller",
          "available": false
        }
      ],
      "role": "USER"
    }
    ```

#### Return Book
- **Endpoint:** `POST /books/return/{id}`
- **Description:** Allows a user to return a rented book.
- **Request Body:**
  ```json
  {
    "userId": 123
  }
  ```
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "firstName": "Rajat",
      "lastName": "Sinha",
      "email": "rajat.sinha@example.com",
      "books": [],
      "role": "USER"
    }
    ```

### Admin Operations

#### Add Book
- **Endpoint:** `POST /books`
- **Description:** Adds a new book to the system. (Admin Only)
- **Request Body:**
  ```json
  {
    "title": "New Book",
    "author": "Admin Author",
    "genre": "Fiction",
    "available": true
  }
  ```
- **Response:**
  - Status: 201 Created
  - Body:
    ```json
    {
      "id": 3,
      "title": "New Book",
      "author": "Admin Author",
      "genre": "Fiction",
      "available": true
    }
    ```

#### Update Book (Admin Only)
- **Endpoint:** `PUT /books/{id}`
- **Description:** Updates information about a specific book. (Admin Only)
- **Request Body:**
  ```json
  {
    "title": "Updated Book Title"
  }
  ```
- **Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "id": 1,
      "title": "Updated Book Title",
      "author": "Jane Smith",
      "genre": "Thriller",
      "available": true
    }
    ```

#### Delete Book (Admin Only)
- **Endpoint:** `DELETE /books/{id}`
- **Description:** Deletes a book from the system. (Admin Only)
- **Response:**
  - Status: 204 No Content

## Sample User

For testing purposes, you can use the following sample user:

- **First Name**: Rajat
- **Last Name**: Sinha
- **Email**: rajat.sinha@example.com
- **Password**: password123
- **Role**: USER

## Admin User

You can also create an admin user with the following credentials:

- **First Name**: Admin
- **Last Name**: User
- **Email**: admin
