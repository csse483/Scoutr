package comcsse483.github.scoutr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by yarlagrt on 1/25/2016.
 */
public class Utils {

    public static ArrayList<Match> generateSampleTeamList() {
        ArrayList<Match> matches = new ArrayList<Match>();
        Random rnd = new Random(5188);
        for (int i = 0; i < 50; i++) {
            int[] newTeams = new int[6];
            for (int j = 0; j < 6; j++) {
                newTeams[j] = rnd.nextInt(6000);
            }
            Match newMatch = new Match(i, Arrays.copyOfRange(newTeams,0,3), Arrays.copyOfRange(newTeams,3,6));
            matches.add(newMatch);
        }
        return matches;
    }
}

