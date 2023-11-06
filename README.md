## WeatherStack API test automation

### Problem Statement:

>Зарегистрироваться на сервисе передачи данных о погоде - https://weatherstack.com/. Бесплатный аккаунт 1000 вызовов в месяц. Поэтому будьте внимательней - должны остаться варианты для запуска во время проверки выполнения задания. Документация по API – https://weatherstack.com/documentation.
Используя любой BDD фреймворк реализовать:
>
>Позитивный тест:
>Запросить текущую погоду минимум по четырем городам на свой выбор.
Распарсить результат, сравнить с ожидаемыми значениями из тестового набора (language, location и т.п.). Ожидаемые тестовые данные можно определить или задать для каждого города корректные (localtime, utc_offset и т.п.), либо можно задать\сгенерировать случайным образом с соблюдением формата (wind_speed, temperature и пр.).
Вывести расхождения по результату сравнения по каждому значению в лог.
> 
> Негативный тест:
Получить 4 варианта ошибок из списка API Errors (на выбор), сравнить с ожидаемым результатом.
> 
>Результат выполнения тестов должен быть в отчете Allure.


### Tests execution

- #### Execute all tests

````
mvn clean test
````

- #### Execute positive tests

````
mvn clean test -Dgroups=positive
````

- #### Execute negative tests

````
mvn clean test -Dgroups=negative
````

### Report generation

- #### Generate Allure report (report will be generated in target/allure-report directory)

````
mvn allure:report
````

- #### Generate Allure report in temporary directory and open report

````
mvn allure:serve
````

#### Log files directory - target/logs