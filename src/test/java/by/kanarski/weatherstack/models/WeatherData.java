package by.kanarski.weatherstack.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private Request request;
    private Location location;
    private Current current;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Request {
        private String language;
        private String unit;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private String name;
        private String country;
        private String region;
        @JsonProperty("latitude")
        @JsonAlias({"lat", "latitude"})
        private String latitude;
        @JsonProperty("longitude")
        @JsonAlias({"lon", "longitude"})
        private String longitude;
        @JsonProperty("utc_offset")
        private String utcOffset;
        @JsonProperty("timezone_id")
        private String timezoneId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Current {

    }
}
