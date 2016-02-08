package comcsse483.github.scoutr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Match;

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
            Match newMatch = new Match(i, Arrays.copyOfRange(newTeams, 0, 3), Arrays.copyOfRange(newTeams, 3, 6));
            matches.add(newMatch);
        }
        return matches;
    }

    public static ArrayList<DataContainer> generateSampleScoutingData() {
        ArrayList<DataContainer> containers = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            DataContainer newContainer = new DataContainer();
            newContainer.setAutoHighGoalAttempted(rnd.nextInt(10));
            newContainer.setAutoLowGoalAttempted(rnd.nextInt(10));
            newContainer.setHighGoalAttempted(rnd.nextInt(10));
            newContainer.setLowGoalAttempted(rnd.nextInt(10));

            newContainer.setAutoHighGoalScored(rnd.nextInt(10));
            newContainer.setAutoLowGoalScored(rnd.nextInt(10));
            newContainer.setHighGoalScored(rnd.nextInt(10));
            newContainer.setLowGoalScored(rnd.nextInt(10));

            newContainer.setCrossedA1(rnd.nextInt(10));
            newContainer.setCrossedA2(rnd.nextInt(10));
            newContainer.setCrossedB1(rnd.nextInt(10));
            newContainer.setCrossedB2(rnd.nextInt(10));
            newContainer.setCrossedC1(rnd.nextInt(10));
            newContainer.setCrossedC2(rnd.nextInt(10));
            newContainer.setCrossedD1(rnd.nextInt(10));
            newContainer.setCrossedD2(rnd.nextInt(10));

            newContainer.setTowerChallenged(rnd.nextBoolean());
            newContainer.setTowerScaled(rnd.nextBoolean());
            containers.add(newContainer);
        }

        return containers;

    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}

