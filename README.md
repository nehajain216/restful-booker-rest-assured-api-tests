# API Tests
This project implements API Tests for https://restful-booker.herokuapp.com/apidoc/index.html using RestAssured.

## How to run?

* Create a configuration file `config.json` and replace the values with correct values.Refer src/test/resources/config.json for sample config file.
* Create test input files in your preferred location and configure the "testDataDirectory" value in config.json file. 

**Note:** You can find sample input csv files in src/test/resources folder

```shell
$ export BOOKER_USERNAME=admin
$ export BOOKER_PASSWORD=password123
$ export CONFIG_FILE=/absolute/path/to/config.json 
$ mvn test
```

## View Allure Reports

Install allure as mentioned in https://docs.qameta.io/allure/

For MacOS run : `brew install allure`

To view Allure report, run: 

```shell
$ allure serve allure-results`
```
