package ca.ubc.cs304.model.vaccine;
/**
 * The intent for this class is to update/store information about a single booster shot
 */
public class RequiredBooster {
    private final String boosterName;
    private final String vacName;
    private double dosage;

    public RequiredBooster(String boosterName, String vacName, double dosage) {
        this.boosterName = boosterName;
        this.vacName = vacName;
        this.dosage = dosage;
    }

    public String getBoosterName() {
        return boosterName;
    }

    public String getVacName() {
        return vacName;
    }

    public double getDosage() {
        return dosage;
    }
}
