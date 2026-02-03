package leonparser.parser;

import leonparser.client.LeonClient;
import leonparser.config.LeonConfig;
import leonparser.dto.LeagueDto;
import leonparser.dto.SportDto;
import leonparser.executor.TasksExecutor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static leonparser.config.LeonConfig.SPORT_LEAGUES_URL;

public class LeonParser {

    private final LeonClient client;
    private final Set<String> targetSports = LeonConfig.TARGET_SPORTS;

    private final TasksExecutor executor;

    public LeonParser() {
        this.client = new LeonClient();
        this.executor = new TasksExecutor();
    }

    public List<SportDto> getFilteredLeagues(List<SportDto> allSports, Set<String> targetSports) {
        return allSports.stream()
                .filter(s -> targetSports.contains(s.getName()))
                .peek(s -> s.getRegions().forEach(region ->
                        region.setLeagues(region.getLeagues().stream()
                                .filter(LeagueDto::isTop)
                                .toList())
                ))
                .toList();
    }

    public List<SportDto> getSports() {
        String json = client.sendRequestGetJson(SPORT_LEAGUES_URL);
        List<SportDto> sports = SportDto.fromJsonToDto(json);
        return  getFilteredLeagues(sports, targetSports);
    }

    public void getMatches(List<SportDto> sports) {
        List<LeagueDto> leagues = sports.stream()
                .flatMap(sport -> sport.getAllLeagues().stream())
                .collect(Collectors.toList());

        executor.submitMatches(leagues, client);
    }

    public List<SportDto> parseAll() {
        List<SportDto> sports = getSports();
        getMatches(sports);
        return sports;
    }
}
