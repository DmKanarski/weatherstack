## WeatherStack API test automation

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