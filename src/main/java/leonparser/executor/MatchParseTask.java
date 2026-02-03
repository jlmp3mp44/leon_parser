package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.model.League;
import leonparser.model.Match;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static leonparser.config.LeonConfig.EXECUTOR;
import static leonparser.config.LeonConfig.MATCHES_URL;

public class MatchParseTask implements Runnable{

    LeonClient client;
    League league;
    CountDownLatch latch;

    public MatchParseTask(LeonClient client, League league, CountDownLatch latch) {
        this.client = client;
        this.league = league;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {

            String matchesJson = client.sendRequestGetJson(
                    String.format(MATCHES_URL, league.getId()));
            List<Match> matches = Match.fromJsonToModel(matchesJson);

            if (matches.size() > 2) matches = matches.subList(0, 2);
            league.setMatches(matches);

            for (Match match : matches) {
                EXECUTOR.submit(new MarketParseTask(client, match));
            }

        } catch (Exception e) {
            System.err.println("Failed to parse matches of league: " + league.getName());
            e.printStackTrace();
        }
        finally {
            latch.countDown();
        }
    }

}
