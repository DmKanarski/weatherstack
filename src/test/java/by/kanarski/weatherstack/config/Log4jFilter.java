package by.kanarski.weatherstack.config;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Log4jFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification reqSpec, FilterableResponseSpecification resSpec, FilterContext filterContext) {
        Response response = filterContext.next(reqSpec, resSpec);
        log.info("Executing request {}", reqSpec.getURI());
        log.info("Response Body:\n{}", response.getBody().asPrettyString());
        return response;
    }
}
