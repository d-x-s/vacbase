package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single COVID19 vaccine
 */
public class COVID19 extends Vaccine{
    private int waitTimeUntilBooster;

    public COVID19(int vacID, String vacName, String type, double dosage, int waitTimeUntilBooster) {
        super(vacID, vacName, type, dosage);
        this.waitTimeUntilBooster = waitTimeUntilBooster;
    }

    public int getWaitTimeUntilBooster() {
        return waitTimeUntilBooster;
    }
}
