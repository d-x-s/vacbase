package ca.ubc.cs304.model.vaccine;
/**
 * The intent for this class is to update/store information about the relationship between
 * AdministeredVaccGivenToPatient and Vaccine
 */
public class Include {
    private final int eventID;
    private final int careCardNumber;
    private final String vacName;


    public Include(int eventID, int careCardNumber, String vacName) {
        this.eventID = eventID;
        this.careCardNumber = careCardNumber;
        this.vacName = vacName;
    }

    public int getEventID() {
        return eventID;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public String getVacName() {
        return vacName;
    }
}
