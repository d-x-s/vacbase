package ca.ubc.cs304.model.vaccine.insert;

public class VaccineNoType {
    private final int vacID;
    private String vacName;
    private double dosage;

    public VaccineNoType(int vacID, String vacName, double dosage) {
        this.vacID = vacID;
        this.vacName = vacName;
        this.dosage = dosage;
    }

    public int getVacID() {
        return vacID;
    }

    public String getVacName() {
        return vacName;
    }

    public double getDosage() {
        return dosage;
    }
}
