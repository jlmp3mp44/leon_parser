package leonparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import leonparser.config.LeonConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDto {
    private String name;
    private List<OutcomeDto> runners;

    public static List<MarketDto> fromJsonToDto(String json) {
        try {
            MarketWrapperDto data = LeonConfig.OBJECT_MAPPER
                    .readValue(json, MarketWrapperDto.class);
            return data.getMarkets();
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse markets json: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}

