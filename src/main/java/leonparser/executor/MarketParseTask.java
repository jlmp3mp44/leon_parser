package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.dto.MatchDTO;
import leonparser.model.Match;
import leonparser.model.Market;

import java.util.List;

import static leonparser.config.LeonConfig.MARKETS_URL;

public class MarketParseTask implements Runnable {
    private final LeonClient client;
    private final Match match;

    public MarketParseTask(LeonClient client, Match match) {
        this.client = client;
        this.match = match;
    }

    @Override
    public void run() {
        try {
            String marketsJson = client.sendRequestGetJson(
                    String.format(MARKETS_URL, match.getId())
            );
            List<Market> markets = MatchDTO.fromJson(marketsJson);
            match.setMarkets(markets);
        } catch (Exception e) {
            System.err.println("Failed to parse markets for match: " + match.getName());
            e.printStackTrace();
        }
    }
}

