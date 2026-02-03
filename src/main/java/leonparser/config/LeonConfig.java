package leonparser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LeonConfig {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Set<String> TARGET_SPORTS = Set.of("Football", "Tennis", "Basketball", "Ice Hockey");

    public static final int THREADS_NUMBER=3;
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREADS_NUMBER);

    public static final String SPORT_LEAGUES_URL = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    public static final String MATCHES_URL = "https://leonbets.com/api-2/betline/changes/all?ctag=en-US&vtag=9c2cd386-31e1-4ce9-a140-28e9b63a9300&league_id=1970324836975359&hideClosed=true&flags=reg,urlv2,orn2,mm2,rrc,nodup";
    public static final String MARKETS_URL = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=%s&flags=reg,urlv2,orn2,mm2,rrc,nodup,smgv2,outv2,wd3";

}
