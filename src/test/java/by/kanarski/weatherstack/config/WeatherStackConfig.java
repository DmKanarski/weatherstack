package by.kanarski.weatherstack.config;

import org.aeonbits.owner.Config;
import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"file:src/test/resources/weatherStackConfig.properties"})
public interface WeatherStackConfig extends Config {

    @Key("weatherStack.apiUrl")
    String apiUrl();

    @Key("weatherStack.accessKey")
    String accessKey();

}