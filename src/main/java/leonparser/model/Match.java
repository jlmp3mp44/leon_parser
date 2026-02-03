package leonparser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import leonparser.config.LeonConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    String name;
    long kickoff;
    long id;

    public static List<Match> fromJsonToModel(String json) {
        try {
            return LeonConfig.getObjectMapper()
                    .readValue(json, new TypeReference<List<Match>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse matches json", e);
        }
    }
}
