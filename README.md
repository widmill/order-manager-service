# order-manager-service

Это приложение предоставляет REST API для управления заказами. Оно разработано с использованием Spring Boot и позволяет выполнять CRUD операции.

Java JDK 11, Maven, PostgreSQL

1. Клонируйте репозиторий:
bash
   git clone https://github.com/widmill/order-manager-service.git
   cd order-manager-service
2. Установите зависимости:
bash
   mvn install
3. Добавить в hibernate.properties свои параметры для БД.
  
4. Запустите приложение:
bash
  mvn spring-boot:run

В проекте есть SQL скрипт и Postman файл с примерами запросов.
