package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single vaccine's type, dosage and availability
 * (A Vaccine 3NF)
 */
public class VacDosage {
    private final String type;
    private double dosage;
    private boolean availability;

    public VacDosage(String type, double dosage, boolean availability) {
        this.type = type;
        this.dosage = dosage;
        this.availability = availability;
    }

    public String getType() {
        return type;
    }

    public double getDosage() {
        return dosage;
    }

    public boolean isAvailability() {
        return availability;
    }
}
