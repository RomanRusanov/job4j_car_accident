[![Build Status](https://travis-ci.org/RomanRusanov/job4j_car_accident.svg?branch=main)](https://travis-ci.org/RomanRusanov/job4j_car_accident)
# job4j_car_accident
В блоке Spring мы будем разрабатывать проект - Автонарушители.
Пользователь добавляет описание автонарушение. 
В заявлении указывает: адрес, номер машины, описание нарушения.

В данном проекте реализовано приложение с использованием технологий Spring-boot, Spring-ORM, Spring-Security, Spring-Web. 
Использован шаблон проектирования MVC, view формы jsp, валидация форм с помощью JS scripts, ORM provider Hibernate, DAO реализация PostgresSQL.

### Вид приложения:
Аутентификации пользователя.

![loginScreen](screenShots/loginScreen.png)

Регистрация

![regNewUser](screenShots/regNewUser.png)

Список всех происшествий

![allAccidents](screenShots/allAccidents.png)

Редактирование происшествия

![editAccident](screenShots/editAccident.png)

Добавление нового происшествия

![createNewAccidents](screenShots/createNewAccidents.png)

### Сборка и запуск

Для сборки использовался Maven.
Перед запуском необходимо создать БД в Postgres и изменить 
[конфигурационный файл](src/main/resources/app.properties) для целевой БД.