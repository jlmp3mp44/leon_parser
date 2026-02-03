package leonparser.parser;

import leonparser.client.LeonClient;
import leonparser.config.LeonConfig;
import leonparser.model.League;
import leonparser.model.Sport;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class LeonParser {
    private final LeonClient client;
    String sportAndLeaguesUrl = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    static Set<String> targetSports = LeonConfig.getTargetSports();

    public LeonParser() {
        this.client = new LeonClient();
    }

    public List<Sport> getSports() {
        String json = client.sendRequestGetJson(sportAndLeaguesUrl);
        List<Sport> sports =  Sport.fromJsonToModel(json);
        return sports.stream()
                .filter(sport -> targetSports.contains(sport.getName()))
                .peek(sport -> {
                    sport.getRegions().forEach(region -> {
                        List<League> topLeagues = region.getLeagues().stream()
                                .filter(League::isTop)
                                .collect(Collectors.toList());
                        region.setLeagues(topLeagues);
                    });
                })
                .collect(Collectors.toList());
    }


}
