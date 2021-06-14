package ca.ubc.cs304.model.vaccine;
/**
 * The intent for this class is to update/store information about the relationship between
 * AdministeredVaccGivenToPatient and Vaccine
 */
public class Include {
    private final int eventID;
    private final int careCardNumber;
    private final int vaccineId;


    public Include(int eventID, int careCardNumber, int vaccineId) {
        this.eventID = eventID;
        this.careCardNumber = careCardNumber;
        this.vaccineId = vaccineId;
    }

    public int getEventID() {
        return eventID;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public int getVacID() {
        return vaccineId;
    }
}
