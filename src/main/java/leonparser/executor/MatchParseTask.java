package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.model.League;
import leonparser.model.Match;

import java.util.List;

import static leonparser.config.LeonConfig.MATCHES_URL;

public class MatchParseTask implements Runnable{

    LeonClient client;
    League league;

    public MatchParseTask(LeonClient client, League league) {
        this.client = client;
        this.league = league;
    }

    @Override
    public void run() {
        try {

            String matchesJson = client.sendRequestGetJson(
                    String.format(MATCHES_URL, league.getId()));
            List<Match> matches = Match.fromJsonToModel(matchesJson);

            if (matches.size() > 2) matches = matches.subList(0, 2);
            league.setMatches(matches);

           /* // 3. Для кожного матчу можна отримати All Markets
            for (Match match : matches) {
                String marketsJson = client.sendRequestGetJson(
                        "https://leonbets.com/api-2/betline/markets?matchId=" + match.getId()
                );
                List<Market> markets = Market.fromJsonToModel(marketsJson);
                match.setMarkets(markets);
            }*/

        } catch (Exception e) {
            System.err.println("Failed to parse matches of league: " + league.getName());
            e.printStackTrace();
        }
    }

}
