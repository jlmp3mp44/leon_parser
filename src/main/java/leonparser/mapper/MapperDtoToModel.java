package leonparser.mapper;

import leonparser.dto.*;
import leonparser.model.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MapperDtoToModel {

    public static List<Sport> sportDtoToModel(List<SportDto> dtos) {
        List<Sport> sports = new ArrayList<>();
        for (SportDto dto : dtos) {
            Sport sport = new Sport();
            sport.setName(dto.getName());
            sport.setLeagues(leagueDtoToModel(dto.getAllLeagues()));
            sports.add(sport);
        }
        return sports;
    }

    public static List<League> leagueDtoToModel(List<LeagueDto> dtos) {
        List<League> leagues = new ArrayList<>();
        for (LeagueDto dto : dtos) {
            League league = new League();
            league.setName(dto.getName());
            league.setMatches(matchDtoToModel(dto.getMatches()));
            leagues.add(league);
        }
        return leagues;
    }

    public static List<Match> matchDtoToModel(List<MatchDto> dtos) {
        List<Match> matches = new ArrayList<>();
        for (MatchDto dto : dtos) {
            Match match = new Match();
            match.setName(dto.getName());
            match.setTime(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dto.getKickoff()),
                    ZoneOffset.UTC));
            match.setId(dto.getId());
            match.setMarkets(marketDtoToModel(dto.getMarkets()));
            matches.add(match);
        }
        return matches;
    }

    public static List<Market> marketDtoToModel(List<MarketDto> dtos) {
        List<Market> markets = new ArrayList<>();
        for (MarketDto dto : dtos) {
            Market market =  new Market();
            market.setName(dto.getName());
           market.setOutcomes(outcomeDtoToModel(dto.getRunners()));
           markets.add(market);
        }
        return markets;
    }

    public static List<Outcome> outcomeDtoToModel(List<OutcomeDto> dtos) {
        List<Outcome> oucomes = new ArrayList<>();
        for (OutcomeDto dto : dtos) {
           Outcome outcome = new Outcome();
           outcome.setId(dto.getId());
           outcome.setName(dto.getName());
           outcome.setCoefficient(dto.getPrice());
           oucomes.add(outcome);
        }
        return oucomes;
    }

}

