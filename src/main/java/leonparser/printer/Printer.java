package leonparser.printer;

import leonparser.model.*;

import java.util.List;

public class Printer {
    public static void printAll(List<Sport> sports) {

        for (Sport sport : sports) {
            for (League league : sport.getLeagues()) {
                System.out.println(sport.getName() + " " + league.getName());
                for (Match match : league.getMatches()) {
                    System.out.println("\t\t" + match.getName() + ", " +
                           match.getTime() + " UTC, " + match.getId());

                    for (Market market : match.getMarkets()) {
                        System.out.println("\t\t\t" + market.getName());
                        for (Outcome outcome : market.getOutcomes()) {
                            System.out.println("\t\t\t\t" + outcome.getName() + ", " +
                                    outcome.getCoefficient() + ", " + outcome.getId());
                        }
                    }
                }
            }
        }
    }

}
