package comcsse483.github.scoutr;

import android.provider.BaseColumns;

/**
 * Created by yarlagrt on 2/1/2016.
 */
public final class DatabaseContract {

    public DatabaseContract() {
        //To stop someone from accidently instantiating the contract class
    }

    public static abstract class TeamMatchEntry implements BaseColumns {
        private static final String COLUMN_NAME_TEAM_NUMBER = "team";
        private static final String COLUMN_NAME_MATCH_NUMBER = "match";

        private static final String COLUMN_NAME_AUTO_LOW_ATTEMPTED =  "autoLowGoalAttempted";
        private static final String COLUMN_NAME_AUTO_LOW_SCORED =  "autoLowGoalScored";
        private static final String COLUMN_NAME_LOW_ATTEMPTED =  "lowGoalAttempted";
        private static final String COLUMN_NAME_LOW_SCORED =  "lowGoalScored";

        private static final String COLUMN_NAME_AUTO_HIGH_ATTEMPTED =  "autoHighGoalAttempted";
        private static final String COLUMN_NAME_AUTO_HIGH_SCORED =  "autoHighGoalScored";
        private static final String COLUMN_NAME_HIGH_ATTEMPTED =  "highGoalAttempted";
        private static final String COLUMN_NAME_HIGH_SCORED =  "highGoalScored";

        private static final String COLUMN_NAME_CROSSED_A1 =  "crossedA1";
        private static final String COLUMN_NAME_CROSSED_A2 =  "crossedA2";
        private static final String COLUMN_NAME_CROSSED_B1 =  "crossedB1";
        private static final String COLUMN_NAME_CROSSED_B2 =  "crossedB2";
        private static final String COLUMN_NAME_CROSSED_C1 =  "crossedC1";
        private static final String COLUMN_NAME_CROSSED_C2 =  "crossedC2";
        private static final String COLUMN_NAME_CROSSED_D1 =  "crossedD1";
        private static final String COLUMN_NAME_CROSSED_D2 =  "crossedD2";

        private static final String COLUMN_NAME_CHALLENGED = "towerChallenged";
        private static final String COLUMN_NAME_SCALED =  "towerScaled";
    }
}
