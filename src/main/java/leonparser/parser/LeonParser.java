package leonparser.parser;

import leonparser.client.LeonClient;
import leonparser.config.LeonConfig;
import leonparser.executor.MatchExecutor;
import leonparser.model.League;
import leonparser.model.Sport;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static leonparser.config.LeonConfig.SPORT_LEAGUES_URL;

public class LeonParser {

    private final LeonClient client;
    private final Set<String> targetSports = LeonConfig.TARGET_SPORTS;

    private final MatchExecutor executor;

    public LeonParser() {
        this.client = new LeonClient();
        this.executor = new MatchExecutor();
    }

    public List<Sport> getSports() {
        String json = client.sendRequestGetJson(SPORT_LEAGUES_URL);
        List<Sport> sports = Sport.fromJsonToModel(json);
        return new SportParser().getSportsAndLeagues(sports, targetSports);
    }

    public void getMatches(List<Sport> sports) {
        List<League> leagues = sports.stream()
                .flatMap(sport -> sport.getAllLeagues().stream())
                .collect(Collectors.toList());

        executor.submitMatches(leagues, client);
    }
}
