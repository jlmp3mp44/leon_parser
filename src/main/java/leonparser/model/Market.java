package leonparser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import leonparser.config.LeonConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {
    private String name;
    private List<Outcome> runners;

    public static List<Market> fromJsonToModel(String json) {
        System.out.println(json);
        try {
             return LeonConfig.OBJECT_MAPPER.readValue(json, new TypeReference<List<Market>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse markets JSON", e);
        }
    }
}

