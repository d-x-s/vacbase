package ca.ubc.cs304.model.vaccine;

/**
 * SQL: COMMAND
 * SELECT V.VacID, V.VacName, V.Type, V.Dosage, VD.Availability
 * FROM Vaccine V, VacDosage VD
 * WHERE V.VacID = VD.VacID
 */

public class VaccineWithAvailability {
    private final int vacID;
    private String vacName;
    private String type;
    private double dosage;
    private char availability;


    public VaccineWithAvailability(int vacID, String vacName, String type, double dosage, char availability) {
        this.vacID = vacID;
        this.vacName = vacName;
        this.type = type;
        this.dosage = dosage;
        this.availability = availability;
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

    public double getDosage() {
        return dosage;
    }

    public char getAvailability() {
        return availability;
    }
}
