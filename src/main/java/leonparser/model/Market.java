package leonparser.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Market {
    private String name;
    private List<Outcome> outcomes;
}

