package ca.ubc.cs304.model.vaccine.insert;

public class VaccineNeither {
    private final int vacID;
    private String vacName;


    public VaccineNeither(int vacID, String vacName, double dosage) {
        this.vacID = vacID;
        this.vacName = vacName;
    }

    public int getVacID() {
        return vacID;
    }

    public String getVacName() {
        return vacName;
    }

}
