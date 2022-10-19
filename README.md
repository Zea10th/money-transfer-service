# Сервис перевода денег

### Общие сведения

Приложение представляет собой интерфейс (REST-сервис), основная задача которого - перевод денег с одной карты на другую по заранее описанной спецификации.
Приложение "Сервис перевода денег" работает в паре с веб-приложением и использует его функционал.

### Архитектура приложения и настройки

Адрес и порт приложения описаны в файле `application.properties`.
Контроллер представляет собой "публичную часть" приложения и воспринимает запросы по следующим входам:
- **POST: /transfer** ожидает json объект с параметрами карты отправления, принимающей карты и суммы транзакции. Возвращает Id сгенерированной транзакции и HttpStatus с результатом обработки запроса. <br>
- **POST: /confirmOperation** ожидает json объект с полученной в результате запроса на входе `/transfer` Id транзакции и проверочного кода (эмуляция SMS), Возвращает Id сгенерированной транзакции и HttpStatus с результатом обработки запроса. <br>
- **GET: /getLogs** возвращает сгенерированный отчет о выполненных операциях.<br>

Репозиторий представляет собой "скрытую часть" приложения и хранит все необходимые сущности: 
- информация о транзакциях (карта отправления, карта получения, сумма)
- информация о проверочном коде SMS для каждой транзакции
- информация о результатах транзакций (удача/неудача).

Контроллер и Репозиторий связаны Сервисом, который выполняет роль утилиты.

### Сборка приложения и контейнеризация

Проект выполнен с использованием сборщика Maven.
Для запуска приложения используется Docker

### Текущий прогресс

* Нарисована схема приложений
* Описана архитектура приложения (где хранятся настройки, формат хранения данных о картах)
* При разработке использовался Spring Boot
* Использован сборщик пакетов Maven
* Код размещен на отдельном репозитории github
* Подключен механизм логирования произведенных операций
* Организовано хранение информации о произведенных операциях
* Написаны unit тесты с использованием mockito
* Написан интеграционный тест с использованием testcontainers
* Созданы соответствующие файлы для размещения приложения в docker, docker-compose


