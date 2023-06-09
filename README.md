<div>

## Курсовой проект "REST API для аукционной системы"
</div>

---
## Описание проекта и его функциональность
Проект для создания и управления лотами в интернет-аукционе по типу “скандинавского аукциона” с фиксированными ставками на лот.

### Реализованы следующие функции:
- Создание лота:
- Перевод лота в состояние “Запущены торги” и “Торги окончены”;
- Прием ставок по лоту;

Так же В API представлены несколько методов для получения аналитической информации по лотам и по работе с лотами и ставками.
Еще предусмотрен экспорт информации по лотам в виде CSV файла.


---
## Запуск приложения
Swagger будет доступен по адресу: http://localhost:8080/swagger-ui/index.html

---
## Стэк технологий

* **Backend**:
    - Java 11
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data JPA
    - Swagger
    - Lombok
* **SQL**:
    - PostgreSQL
    - Liquibase
