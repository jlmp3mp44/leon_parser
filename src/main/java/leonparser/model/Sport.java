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
public class Sport {
    private String name;
    private List<Region> regions;

    public static List<Sport> fromJsonToModel(String json) {
        try {
            return LeonConfig.getObjectMapper()
                    .readValue(json, new TypeReference<List<Sport>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse sports json", e);
        }
    }

    public List<League> getAllLeagues() {
        return regions.stream()
                .flatMap(r -> r.leagues.stream())
                .toList();
    }
}
