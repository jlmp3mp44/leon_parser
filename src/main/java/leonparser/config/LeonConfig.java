package leonparser.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class LeonConfig {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    private static final Set<String> targetSports = Set.of("Football", "Tennis", "Basketball", "Ice Hockey");
    public static Set<String> getTargetSports() {
        return targetSports;
    }
}
