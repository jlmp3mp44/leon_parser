package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.model.League;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static leonparser.config.LeonConfig.EXECUTOR;

public class MatchExecutor {

    public void submitMatches(List<League> leagues, LeonClient client) {
        CountDownLatch latch = new CountDownLatch(leagues.size());
        for (League league : leagues) {
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
