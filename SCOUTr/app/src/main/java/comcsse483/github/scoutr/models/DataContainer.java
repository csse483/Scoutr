package comcsse483.github.scoutr.models;

import comcsse483.github.scoutr.Constants;

/**
 * A container for data that will be collected during mMatches.
 * Separated so that data fields can be customized on the fly.
 * Good OOP.
 */
public class DataContainer {
    private int mTeamId;
    private int mMatchNumber;

    private int autoLowGoalAttempted;
    private int autoLowGoalScored;
    private int lowGoalAttempted;
    private int lowGoalScored;

    private int autoHighGoalAttempted;
    private int autoHighGoalScored;
    private int highGoalAttempted;
    private int highGoalScored;


    private int crossedA1;
    private int crossedA2;
    private int crossedB1;
    private int crossedB2;
    private int crossedC1;
    private int crossedC2;
    private int crossedD1;
    private int crossedD2;

    private boolean towerChallenged;
    private boolean towerScaled;

    public DataContainer() {
        //Empty constructor only used for testing
    }

    /**
     * Constructor that assigns a teamId. This is to be used exclusively in the future.
     *
     * @param teamId
     */
    public DataContainer(int teamId) {
        mTeamId = teamId;
    }

    /**
     * Clunky method to set the corresponding data based on the name of the incoming  data entry.
     *
     * @param dataName
     * @param data
     * @return
     */
    public boolean setData(String dataName, Object data) {
        //This is sooooooooooo ugly
        if (dataName.equals(Constants.DATA_NAMES[0])) {
            setmTeamId((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[1])) {
            setMatchNumber((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[2])) {
            setAutoLowGoalAttempted((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[3])) {
            setAutoLowGoalScored((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[4])) {
            setLowGoalAttempted((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[5])) {
            setLowGoalScored((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[6])) {
            setAutoHighGoalAttempted((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[7])) {
            setAutoHighGoalScored((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[8])) {
            setHighGoalAttempted((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[9])) {
            setHighGoalScored((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[10])) {
            setCrossedA1((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[11])) {
            setCrossedA2((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[12])) {
            setCrossedB1((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[13])) {
            setCrossedB2((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[14])) {
            setCrossedC1((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[15])) {
            setCrossedC2((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[16])) {
            setCrossedD1((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[17])) {
            setCrossedD2((int) data);
        } else if (dataName.equals(Constants.DATA_NAMES[18])) {
            setTowerChallenged((Boolean) data);
        } else if (dataName.equals(Constants.DATA_NAMES[19])) {
            setTowerScaled((Boolean) data);
        }

        return false;
    }

    public int getMatchNumber() {
        return mMatchNumber;
    }

    public void setMatchNumber(int mMatchNumber) {
        this.mMatchNumber = mMatchNumber;
    }

    public int getAutoLowGoalAttempted() {
        return autoLowGoalAttempted;
    }

    public void setAutoLowGoalAttempted(int autoLowGoalAttempted) {
        this.autoLowGoalAttempted = autoLowGoalAttempted;
    }

    public int getAutoLowGoalScored() {
        return autoLowGoalScored;
    }

    public void setAutoLowGoalScored(int autoLowGoalScored) {
        this.autoLowGoalScored = autoLowGoalScored;
    }

    public int getLowGoalAttempted() {
        return lowGoalAttempted;
    }

    public void setLowGoalAttempted(int lowGoalAttempted) {
        this.lowGoalAttempted = lowGoalAttempted;
    }

    public int getLowGoalScored() {
        return lowGoalScored;
    }

    public void setLowGoalScored(int lowGoalScored) {
        this.lowGoalScored = lowGoalScored;
    }

    public int getAutoHighGoalAttempted() {
        return autoHighGoalAttempted;
    }

    public void setAutoHighGoalAttempted(int autoHighGoalAttempted) {
        this.autoHighGoalAttempted = autoHighGoalAttempted;
    }

    public int getAutoHighGoalScored() {
        return autoHighGoalScored;
    }

    public void setAutoHighGoalScored(int autoHighGoalScored) {
        this.autoHighGoalScored = autoHighGoalScored;
    }

    public int getHighGoalAttempted() {
        return highGoalAttempted;
    }

    public void setHighGoalAttempted(int highGoalAttempted) {
        this.highGoalAttempted = highGoalAttempted;
    }

    public int getHighGoalScored() {
        return highGoalScored;
    }

    public void setHighGoalScored(int highGoalScored) {
        this.highGoalScored = highGoalScored;
    }

    public int getCrossedA1() {
        return crossedA1;
    }

    public void setCrossedA1(int crossedA1) {
        this.crossedA1 = crossedA1;
    }

    public int getCrossedA2() {
        return crossedA2;
    }

    public void setCrossedA2(int crossedA2) {
        this.crossedA2 = crossedA2;
    }

    public int getCrossedB1() {
        return crossedB1;
    }

    public void setCrossedB1(int crossedB1) {
        this.crossedB1 = crossedB1;
    }

    public int getCrossedB2() {
        return crossedB2;
    }

    public void setCrossedB2(int crossedB2) {
        this.crossedB2 = crossedB2;
    }

    public int getCrossedC1() {
        return crossedC1;
    }

    public void setCrossedC1(int crossedC1) {
        this.crossedC1 = crossedC1;
    }

    public int getCrossedC2() {
        return crossedC2;
    }

    public void setCrossedC2(int crossedC2) {
        this.crossedC2 = crossedC2;
    }

    public int getCrossedD1() {
        return crossedD1;
    }

    public void setCrossedD1(int crossedD1) {
        this.crossedD1 = crossedD1;
    }

    public int getCrossedD2() {
        return crossedD2;
    }

    public void setCrossedD2(int crossedD2) {
        this.crossedD2 = crossedD2;
    }

    public boolean isTowerChallenged() {
        return towerChallenged;
    }

    public void setTowerChallenged(boolean towerChallenged) {
        this.towerChallenged = towerChallenged;
    }

    public boolean isTowerScaled() {
        return towerScaled;
    }

    public void setTowerScaled(boolean towerScaled) {
        this.towerScaled = towerScaled;
    }

    @Override
    public String toString() {
        //TODO: Update dataContainer toString to show each field
        return "" + autoHighGoalAttempted + " " + autoHighGoalScored + " " + autoLowGoalAttempted
                + " " + autoLowGoalScored + " " + crossedA1 + " " + crossedA2 + " " + crossedB1
                + " " + crossedB2 + " " + crossedC1 + " " + crossedC2 + " " + crossedD1 + " " +
                crossedD2 + " " + Boolean.toString(isTowerChallenged()) + " " + Boolean.toString(isTowerScaled());
    }

    public int getmTeamId() {
        return mTeamId;
    }

    public void setmTeamId(int mTeamId) {
        this.mTeamId = mTeamId;
    }
}
