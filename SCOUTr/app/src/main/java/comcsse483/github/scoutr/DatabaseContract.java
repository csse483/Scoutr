package comcsse483.github.scoutr;

import android.provider.BaseColumns;

/**
 * A class defining all the database constants.
 */
public final class DatabaseContract {

    public DatabaseContract() {
        //To stop someone from accidently instantiating the contract class
    }

    public static abstract class TeamMatchEntry implements BaseColumns {
        public static final String COLUMN_NAME_TEAM_NUMBER = "team";
        public static final String COLUMN_NAME_MATCH_NUMBER = "match";

        public static final String COLUMN_NAME_AUTO_LOW_ATTEMPTED =  "autoLowGoalAttempted";
        public static final String COLUMN_NAME_AUTO_LOW_SCORED =  "autoLowGoalScored";
        public static final String COLUMN_NAME_LOW_ATTEMPTED =  "lowGoalAttempted";
        public static final String COLUMN_NAME_LOW_SCORED =  "lowGoalScored";

        public static final String COLUMN_NAME_AUTO_HIGH_ATTEMPTED =  "autoHighGoalAttempted";
        public static final String COLUMN_NAME_AUTO_HIGH_SCORED =  "autoHighGoalScored";
        public static final String COLUMN_NAME_HIGH_ATTEMPTED =  "highGoalAttempted";
        public static final String COLUMN_NAME_HIGH_SCORED =  "highGoalScored";

        public static final String COLUMN_NAME_CROSSED_A1 =  "crossedA1";
        public static final String COLUMN_NAME_CROSSED_A2 =  "crossedA2";
        public static final String COLUMN_NAME_CROSSED_B1 =  "crossedB1";
        public static final String COLUMN_NAME_CROSSED_B2 =  "crossedB2";
        public static final String COLUMN_NAME_CROSSED_C1 =  "crossedC1";
        public static final String COLUMN_NAME_CROSSED_C2 =  "crossedC2";
        public static final String COLUMN_NAME_CROSSED_D1 =  "crossedD1";
        public static final String COLUMN_NAME_CROSSED_D2 =  "crossedD2";

        public static final String COLUMN_NAME_TOWER_CHALLENGED = "towerChallenged";
        public static final String COLUMN_NAME_TOWER_SCALED =  "towerScaled";


        public static String[] getListOfDefenses() {
            return new String[] {
                    COLUMN_NAME_CROSSED_A1,
                    COLUMN_NAME_CROSSED_A2,
                    COLUMN_NAME_CROSSED_B1,
                    COLUMN_NAME_CROSSED_B2,
                    COLUMN_NAME_CROSSED_C1,
                    COLUMN_NAME_CROSSED_C2,
                    COLUMN_NAME_CROSSED_D1,
                    COLUMN_NAME_CROSSED_D2
            };
        }
    }
}
