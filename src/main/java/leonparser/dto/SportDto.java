package leonparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import leonparser.config.LeonConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportDto {
    private String name;
    private List<RegionDto> regions;

    public static List<SportDto> fromJsonToDto(String json) {
        try {
            return LeonConfig.OBJECT_MAPPER
                    .readValue(json, new TypeReference<List<SportDto>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse sports json: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<LeagueDto> getAllLeagues() {
        return regions.stream()
                .flatMap(r -> r.leagues.stream())
                .toList();
    }
}

