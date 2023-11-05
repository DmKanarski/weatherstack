package by.kanarski.weatherstack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherError {

    private Boolean success;
    private ErrorObject error;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorObject {
        private Integer code;
        private String type;
        private String info;
    }
}
