package leonparser;

import leonparser.client.LeonClient;
import leonparser.config.LeonConfig;
import leonparser.model.League;
import leonparser.model.Match;
import leonparser.model.Sport;
import leonparser.parser.LeonParser;
import leonparser.parser.SportParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeonParser parser = new LeonParser();

        List<Sport> sports = parser.getSports();
        parser.getMatches(sports);

        for (Sport sport : sports) {
            System.out.println("Sport: " + sport.getName());
            for (League league : sport.getAllLeagues()) {
                System.out.println("  League: " + league.getName());
                if (league.getMatches() != null) {
                    for (Match match : league.getMatches()) {
                        System.out.println("    Match: " + match.getName());
                    }
                }
            }
        }

    }
}