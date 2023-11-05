package by.kanarski.weatherstack.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    public static Map<String, String> converToStringMap(Object model) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> result = objectMapper.convertValue(model, new TypeReference<>() {});
        return convertToStringMap(result);
    }

    public static Map<String, String> convertToStringMap(Map<?, ?> map) {
        Map<String, String> result = new HashMap<>();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map) {
                result.putAll(convertToStringMap((Map<?, ?>) value));
            } else if (value != null){
                result.put(key.toString(), value.toString());
            }
        }
        return result;
    }

    public static String convertToKebabCase(String source) {
        return source.replaceAll("[A-Z\\s]", "_").toLowerCase();
    }
}
