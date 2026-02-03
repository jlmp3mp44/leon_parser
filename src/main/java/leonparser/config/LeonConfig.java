package leonparser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.Set;


public class LeonConfig {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Set<String> TARGET_SPORTS = Set.of("Football", "Tennis", "Basketball", "Ice Hockey");
    public static final int THREADS_NUMBER=3;
    public static final String SPORT_LEAGUES_URL = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    public static final String MATCHES_URL = "https://leonbets.com/api-2/betline/changes/all?ctag=en-US&vtag=9c2cd386-31e1-4ce9-a140-28e9b63a9300&league_id=1970324836975359&hideClosed=true&flags=reg,urlv2,orn2,mm2,rrc,nodup";

}
