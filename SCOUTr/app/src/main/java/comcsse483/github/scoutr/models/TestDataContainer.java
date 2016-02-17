package comcsse483.github.scoutr.models;

/**
 * A test data container used during database testing.
 */
public class TestDataContainer {
    private int teamNumber;

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    private double shotPercetage;
    private int numCrossings;
    private boolean towerScaled;
    private boolean towerChallenged;

    public TestDataContainer() {
    }

    public double getShotPercetage() {
        return shotPercetage;
    }

    public void setShotPercetage(double shotPercetage) {
        this.shotPercetage = shotPercetage;
    }

    public int getNumCrossings() {
        return numCrossings;
    }

    public void setNumCrossings(int numCrossings) {
        this.numCrossings = numCrossings;
    }

    public boolean isTowerScaled() {
        return towerScaled;
    }

    public void setTowerScaled(boolean towerScaled) {
        this.towerScaled = towerScaled;
    }

    public boolean isTowerChallenged() {
        return towerChallenged;
    }

    public void setTowerChallenged(boolean towerChallenged) {
        this.towerChallenged = towerChallenged;
    }
}
