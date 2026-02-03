package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.model.League;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatchExecutor {
    private final ExecutorService executor;

    public MatchExecutor(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void submitMatches(List<League> leagues, LeonClient client) {
        for (League league : leagues) {
            executor.submit(new MatchParseTask(client, league));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
