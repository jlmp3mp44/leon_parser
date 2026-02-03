package leonparser.parser;

import leonparser.model.League;
import leonparser.model.Sport;

import java.util.List;
import java.util.Set;

public class SportParser{
    public List<Sport> getSportsAndLeagues(List<Sport> allSports, Set<String> targetSports) {
        return allSports.stream()
                .filter(s -> targetSports.contains(s.getName()))
                .peek(s -> s.getRegions().forEach(region ->
                        region.setLeagues(region.getLeagues().stream()
                                .filter(League::isTop)
                                .toList())
                ))
                .toList();
    }

}

