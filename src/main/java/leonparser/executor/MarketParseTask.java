package leonparser.executor;

import leonparser.client.LeonClient;
import leonparser.dto.MarketDto;
import leonparser.dto.MatchDto;

import java.util.List;

import static leonparser.config.LeonConfig.MARKETS_URL;

public class MarketParseTask implements Runnable {
    private final LeonClient client;
    private final MatchDto match;

    public MarketParseTask(LeonClient client, MatchDto match) {
        this.client = client;
        this.match = match;
    }

    @Override
    public void run() {
        try {
            String marketsJson = client.sendRequestGetJson(
                    String.format(MARKETS_URL, match.getId())
            );
            List<MarketDto> markets = MarketDto.fromJsonToDto(marketsJson);
            match.setMarkets(markets);
        } catch (Exception e) {
            System.err.println("Failed to parse markets for match: " + match.getName());
            e.printStackTrace();
        }
    }
}

