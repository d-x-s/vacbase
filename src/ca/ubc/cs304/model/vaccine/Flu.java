package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single Flu vaccine
 */
public class Flu extends Vaccine {
    private int minAge;

    public Flu(String vacName, String type, double dosage, int minAge) {
        super(vacName, type, dosage);
        this.minAge = minAge;
    }

    public int getMinAge() {
        return minAge;
    }
}
