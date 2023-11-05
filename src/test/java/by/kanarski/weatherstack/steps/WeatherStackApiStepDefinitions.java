package by.kanarski.weatherstack.steps;

import by.kanarski.weatherstack.config.Configs;
import by.kanarski.weatherstack.config.Log4jFilter;
import by.kanarski.weatherstack.config.WeatherStackConfig;
import by.kanarski.weatherstack.models.WeatherData;
import by.kanarski.weatherstack.models.WeatherError;
import by.kanarski.weatherstack.utils.DataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WeatherStackApiStepDefinitions {

    private static final WeatherStackConfig WEATHER_STACK_CONFIG = Configs.weatherStackConfig;
    private final Map<String, Response> responseMap = new HashMap<>();

    @Given("^I configure an API client to access the WeatherStack service$")
    public void setApiKey() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(WEATHER_STACK_CONFIG.apiUrl())
                .addFilter(new Log4jFilter())
                .build().queryParam("access_key", WEATHER_STACK_CONFIG.accessKey());
    }

    @When("^I send request to the /'(.*)' endpoint with the following parameters and store the response in '(.*)':$")
    public void sendRequestToEndpointWithParameters(String endpoint, String responseAlias, Map<String, String> requestDataMap) {
        Response response = RestAssured.given().queryParams(requestDataMap).get(endpoint);
        responseMap.put(responseAlias, response);
    }

    @Then("^API status code for response '(.*)' should be '(\\d+)'$")
    public void checkStatusCode(String responseAlias, int expectedStatusCode) {
        int actualStatusCode = responseMap.get(responseAlias).statusCode();
        Assertions
                .assertThat(responseMap.get(responseAlias).statusCode())
                .withFailMessage("The expected status code is '%d', the actual status code is '%d'",
                        expectedStatusCode, actualStatusCode)
                .isEqualTo(expectedStatusCode);
    }

    @Then("^response '(.*)' of type '(weather|error)' should contain the following data:$")
    public void checkResponseData(String responseAlias, String responseType, Map<String, String> expectedDataMap) {
        Class<?> responseClass = responseType.equals("error") ? WeatherError.class : WeatherData.class;
        Object responseDataObject = responseMap.get(responseAlias).as(responseClass);
        Map<String, String> actualDataMap = DataUtils.converToStringMap(responseDataObject);
        SoftAssertions softAssert = new SoftAssertions();
        for (String key : expectedDataMap.keySet()) {
            String expectedValue = expectedDataMap.get(key);
            String actualValue = actualDataMap.get(key) == null
                    ? actualDataMap.get(DataUtils.convertToKebabCase(key))
                    : actualDataMap.get(key);
            softAssert
                    .assertThat(expectedValue)
                    .withFailMessage("The expected value of the '%s' field is '%s', the actual value is '%s'",
                            key, expectedValue, actualValue)
                    .isEqualTo(actualValue);
            if (!expectedValue.equals(actualValue)) {
                log.error("The expected value of the '{}' field is '{}', the actual value is '{}'",
                        key, expectedValue, actualValue);
            }
        }
        softAssert.assertAll();
    }
}