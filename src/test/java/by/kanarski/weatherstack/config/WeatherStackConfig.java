package by.kanarski.weatherstack.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/weatherStackConfig.properties"})
public interface WeatherStackConfig extends Config {

    @Key("weatherStack.apiUrl")
    String apiUrl();

    @Key("weatherStack.accessKey")
    String accessKey();

}