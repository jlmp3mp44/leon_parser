package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.dto.LeagueDto;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static leonparser.config.LeonConfig.EXECUTOR;

public class TasksExecutor {

    public void submitMatches(List<LeagueDto> leagues, LeonClient client) {
        CountDownLatch latch = new CountDownLatch(leagues.size());
        for (LeagueDto league : leagues) {
            EXECUTOR.submit(new MatchParseTask(client, league, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        EXECUTOR.shutdown();
    }
}
