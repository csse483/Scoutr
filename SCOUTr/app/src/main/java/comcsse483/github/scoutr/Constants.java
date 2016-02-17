package comcsse483.github.scoutr;

/**
 * A container for all the constants used in the app.
 */
public class Constants {
    public final static String KEY_TOURNAMENT = "KEY_TOURNAMENT";
    public final static String TAG = "SCOUTr";
    public final static String API_ID = "frc5188:scouting_app:v0.1";

    /*---------------------------------------Used By RecordDataAdapter----------------------------*/
    // Describes the type of data being collected
    public static final String[] DATA_FIELDS = new String[]{
            "int", "int", "int", "int", "int", "int", "int", "int", "int", "int", "int", "int",
            "boolean", "boolean"
    };

    public static final String[] DATA_NAMES = new String[]{
            "Auto Low Goals Attempted", "Auto Low Goals Scored",
            "Low Goals Attempted", "Low Goals Scored",
            "Number of Times Crossed A1",
            "Number of Times Crossed A2",
            "Number of Times Crossed B1",
            "Number of Times Crossed B2",
            "Number of Times Crossed C1",
            "Number of Times Crossed C2",
            "Number of Times Crossed D1",
            "Number of Times Crossed D2",
            "Tower Challenged",
            "Tower Scaled"
    };
    /*--------------------------------------------------------------------------------------------*/
}
