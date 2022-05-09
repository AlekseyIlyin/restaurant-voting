Java Enterprise выпускной проект
===============================

### Разработайте и внедрите REST API с использованием Hibernate/Spring/SpringMVC (или Spring-Boot) без внешнего интерфейса.

## Задача:
### Создайте систему голосования, чтобы решить, где пообедать.

- 2 типа пользователей: администратор и обычные пользователи
- Администратор может ввести ресторан и его обеденное меню дня (обычно 2-5 пунктов, только название блюда и цена)
- Меню меняется каждый день (обновления делают администраторы)
- Пользователи могут проголосовать за ресторан, в котором они хотят пообедать.
- Учитывается только один голос от пользователя
- Если пользователь проголосует снова в тот же день:
  - Если до 11:00, мы предполагаем, что он передумал.
  - Если после 11:00, то уже поздно, голосовать нельзя
- Каждый ресторан предлагает новое меню каждый день.

В результате предоставьте ссылку на репозиторий github. Он должен содержать код, README.md с документацией по API и пару команд curl для его проверки (лучше — ссылку на Swagger).

<em> P.S.: Убедитесь, что все работает с последней версией, которая есть на github :)</em>

<em>P.P.S.: Предположим, что ваш API будет использоваться разработчиком внешнего интерфейса для создания внешнего интерфейса поверх него.</em>


Java Enterprise senior project
===============================

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

### 15.05.22: Дедлайн на сдачу [выпускного проекта](https://github.com/JavaOPs/topjava/blob/master/graduation.md)
### 25.05.22: Получение дипломов для участников [Дипломной программы](https://javaops.ru/view/register/diploma)

### Examples of calling the service:
- Получить всех пользователей: curl -u admin@gmail.com:admin http://localhost:8080/rest/admin/users
- Получить все рестораны: curl -u user@yandex.ru:password http://localhost:8080/rest/restaurant
- Получить ресторан с id 100004: curl -u user@yandex.ru:password http://localhost:8080/rest/restaurant/100004
- Получить меню по всем ресторанам на дату (если дата не указана - то на текущую): curl -u user@yandex.ru:password http://localhost:8080/rest/menu