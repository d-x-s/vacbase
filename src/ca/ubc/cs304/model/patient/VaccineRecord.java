package ca.ubc.cs304.model.patient;

/**
 * The intent for this class is to update/store information about a single patient's vaccine record
 */
public class VaccineRecord {
    private final int careCardNumber;
    private final int recordID;
    private int eventID;

    public VaccineRecord(int careCardNumber, int recordID, int eventID) {
        this.careCardNumber = careCardNumber;
        this.recordID = recordID;
        this.eventID = eventID;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getEventID() {
        return eventID;
    }
}
