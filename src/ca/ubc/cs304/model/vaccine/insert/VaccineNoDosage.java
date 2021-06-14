package ca.ubc.cs304.model.vaccine.insert;

public class VaccineNoDosage {
    private final int vacID;
    private String vacName;
    private String type;

    public VaccineNoDosage(int vacID, String vacName, String type) {
        this.vacID = vacID;
        this.vacName = vacName;
        this.type = type;

    }

    public int getVacID() {
        return vacID;
    }

    public String getVacName() {
        return vacName;
    }

    public String getType() {
        return type;
    }

}
