package leonparser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import leonparser.config.LeonConfig;
import leonparser.responces.MatchResponse;
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
            MatchResponse response = LeonConfig.OBJECT_MAPPER
                    .readValue(json, MatchResponse.class);
            return response.getData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse matches json", e);
        }
    }

}
