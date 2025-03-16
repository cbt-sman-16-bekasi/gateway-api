package id.co.ist.los.gateway.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    @SneakyThrows
    public static String objectAsStringJson(Object data) {
        return objectMapper.writeValueAsString(data);
    }

    @SneakyThrows
    public static <T> T stringJsonAsObject(String json, Class<T> clazz)  {
        return objectMapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> T stringJsonAsObject(String json, TypeReference<T> clazz)  {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> T convertObject(Object from, Class<T> destClazz) {
        return objectMapper.convertValue(from, destClazz);
    }

    public static <T> T convertObject(Object from, TypeReference<T> type) {
        return objectMapper.convertValue(from, type);
    }

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
