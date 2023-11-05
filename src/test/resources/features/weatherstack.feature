@weatherStackApi
Feature: WeatherStack API tests

  Background:
    Given I configure an API client to access the WeatherStack service

  @positive
  Scenario Outline: Validation of the immutable response data for the query /current?query=<city>&units=<unit>
    When I send request to the /'current' endpoint with the following parameters and store the response in 'response':
      | query | <city> |
      | units | <unit> |
    Then API status code for response 'response' should be '200'
    And response 'response' of type 'weather' should contain the following data:
      | name        | <city>        |
      | unit        | <unit>        |
      | country     | <country>     |
      | latitude    | <latitude>    |
      | longitude   | <longitude>   |
      | timezone id | <timezone id> |

    Examples:
      | city     | unit | country                  | latitude | longitude | timezone id      |
      | Tbilisi  | f    | Georgia                  | 41.725   | 44.791    | Asia/Tbilisi     |
      | New York | f    | United States of America | 40.714   | -74.006   | America/New_York |
      | Moscow   | m    | Russia                   | 55.752   | 37.616    | Europe/Moscow    |
      | Wroclaw  | f    | Poland                   | 51.100   | 17.033    | Europe/Warsaw    |
      | Minsk    | s    | Belarus                  | 53.900   | 27.567    | Europe/Minsk     |

  @negative
  Scenario Outline: Validation of the response error object for the query /<endpoint>?query=<city>&units=<unit>
    When I send request to the /'<endpoint>' endpoint with the following parameters and store the response in 'response':
      | query    | <city>     |
      | units    | <unit>     |
      | language | <language> |
    Then API status code for response 'response' should be '200'
    And response 'response' of type 'error' should contain the following data:
      | code | <error code>    |
      | type | <error type>    |
      | info | <error message> |

    Examples:
      | endpoint   | city   | unit   | language | error code | error type                               | error message                                                                                                             |
      | foobar     | Moscow |        |          | 103        | invalid_api_function                     | This API Function does not exist.                                                                                         |
      | current    | Moscow |        | ru       | 105        | function_access_restricted               | Access Restricted - Your current Subscription Plan does not support this API Function.                                    |
      | current    |        |        |          | 601        | missing_query                            | Please specify a valid location identifier using the query parameter.                                                     |
      | historical | Moscow |        |          | 603        | historical_queries_not_supported_on_plan | Your current subscription plan does not support historical weather data. Please upgrade your account to use this feature. |
      | current    | Moscow | foobar |          | 606        | invalid_unit                             | You have specified an invalid unit. Please try again or refer to our API documentation.                                   |
