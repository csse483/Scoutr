package comcsse483.github.scoutr;

/**
 * A container for data that will be collected during matches.
 * Separated so that data fields can be customized on the fly.
 * Good OOP.
 */
public class DataContainer {
    private int autoLowGoalAttempted;
    private int autoLowGoalScored;
    private int lowGoalAttempted;
    private int lowGoalScored;
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


}
