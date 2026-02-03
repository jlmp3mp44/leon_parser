package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.dto.LeagueDto;
import leonparser.dto.MatchDto;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static leonparser.config.LeonConfig.EXECUTOR;
import static leonparser.config.LeonConfig.MATCHES_URL;

public class MatchParseTask implements Runnable{

    LeonClient client;
    LeagueDto league;
    CountDownLatch latch;

    public MatchParseTask(LeonClient client, LeagueDto league, CountDownLatch latch) {
        this.client = client;
        this.league = league;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            String matchesJson = client.sendRequestGetJson(
                    String.format(MATCHES_URL, league.getId()));
            List<MatchDto> matches = MatchDto.fromJsonToDto(matchesJson);

            if (matches.size() > 2) matches = matches.subList(0, 2);
            league.setMatches(matches);

            for (MatchDto match : matches) {
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
