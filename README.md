Java Enterprise project
=======================

### Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

## The task is:

### Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
  - If it is before 11:00 we assume that he changed his mind.
  - If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).

<em>P.S.: Make sure everything works with latest version that is on github :)</em>

<em>P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.</em>

### use:
- Spring Boot
- Spring Data JPA
- Jackson
- HSQL Database (Postgres)
- JUnit 5
- Swagger

### Documentation: http://localhost:8080/swagger-ui.html#/

### Examples of calling the service:
- get all users: curl -u admin@gmail.com:admin http://localhost:8080/rest/admin/users
- get all restaurants: curl -u user@yandex.ru:password http://localhost:8080/rest/restaurants
- get restaurant with id 100004: curl -u user@yandex.ru:password http://localhost:8080/rest/restaurants/100004
- get all menus by date (if date missing then current): curl -u user@yandex.ru:password http://localhost:8080/rest/menus

#### User : password:
- admin@gmail.com : admin
- user@yandex.ru : password