# LibraryPrototype

Major Tech Stack used to provide solution:
Spring Boot
Hibernate JPA Implementation
H2 database
jUnit Testing Framework
Postman for endpoint testing

Assumptions:
Books can be uniquely identified by title and authorname. 
So while book update, Expected user to provide this unique combination rather than internal system ID for API calls.

Java-doc is present at /doc folder.

Also below are endpoints that can be tested:

1. 
url:http://localhost:8080/api/library/updateBook
body:{
    "book_title": "Power Of Now",
    "author_name": "Eckhart Tolle",
    "ratings": { "languageAccuracy": 3 }
}
method: PUT


2. 
url: http://localhost:8080/api/library/getBooksForLibrary/LIB-988-453
method: GET

3. 
URL: http://localhost:8080/api/library/getAllBooks
Method: GET