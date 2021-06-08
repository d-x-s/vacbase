package ca.ubc.cs304.model.vaccine;

import java.sql.Date;
/**
 * The intent for this class is to update/store information about a single AdministeredVaccGivenToPatient
 * (Aggregation Vaccine event, Nurse and Administered)
 */
public class AdministeredVaccGivenToPatient {
    private final int eventID;
    private int nurseID;
    private final int careCardNumber;
    private Date date;

    public AdministeredVaccGivenToPatient(int eventID, int nurseID, int careCardNumber, Date date) {
        this.eventID = eventID;
        this.nurseID = nurseID;
        this.careCardNumber = careCardNumber;
        this.date = date;
    }

    public int getEventID() {
        return eventID;
    }

    public int getNurseID() {
        return nurseID;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public Date getDate() {
        return date;
    }
}
