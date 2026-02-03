package leonparser;

import leonparser.client.LeonClient;
import leonparser.config.LeonConfig;
import leonparser.model.League;
import leonparser.model.Sport;
import leonparser.parser.LeonParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeonParser parser = new LeonParser();

        List<Sport> sports = parser.getSports();

        for (Sport sport : sports) {
            System.out.println("Sport: " + sport.getName());
            sport.getAllLeagues().forEach(league ->
                    System.out.println("  League: " + league.getName()));
        }
    }
}