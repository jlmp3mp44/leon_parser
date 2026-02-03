package leonparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import leonparser.config.LeonConfig;
import leonparser.model.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {
    private List<Market> markets;

    public static List<Market> fromJson(String json) {
        try {
            MatchDTO dto = LeonConfig.OBJECT_MAPPER.readValue(json, MatchDTO.class);
            return dto != null ? dto.getMarkets() : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
