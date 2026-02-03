package leonparser.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import leonparser.model.Match;
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
public class MatchResponse {
    private List<Match> data;
}

