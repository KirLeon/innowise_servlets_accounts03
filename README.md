# innowise_servlets_task
Task:
To prepare a data accounting system for company employees:
    The admin can edit, add, delete, view information about employees, the employee can only view
    Passwords in the database are stored ONLY in hashed form
    Stack: Servlet API 3.0 and later, Jackson, Lombok, any relational DBMS to taste, responses from the server only in JSON, the use of MapStruct is welcome.
    Implementing singleton pattern in service classes, repositories, DAOs, e.t.c. is required
    
FLYWAY: to migrate database with flyway use next command: $ mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf

Project is using MySql to store data, so you need to have installed mysql on your device

This software was tested with Postman, JWT token is stored in header just like that:
![image](https://user-images.githubusercontent.com/113788413/229105392-e4815022-ecb1-4ded-83eb-3868f785b907.png)

!Request and responce body are in JSON

