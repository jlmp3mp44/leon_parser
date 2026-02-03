package leonparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class MatchDto {
    String name;
    long kickoff;
    long id;
    List<MarketDto> markets;

    public static List<MatchDto> fromJsonToDto(String json) {
        try {
            MatchWrapperDto data = LeonConfig.OBJECT_MAPPER
                    .readValue(json, MatchWrapperDto.class);
            return data.getData();
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse matches json: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
