package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about the relationship between
 * VaccineEvent and Facility
 */
public class HappensIn {
    private final int eventID;
    private final int careCardNumber;
    private final int facilityID;

    public HappensIn(int eventID, int careCardNumber, int facilityID) {
        this.eventID = eventID;
        this.careCardNumber = careCardNumber;
        this.facilityID = facilityID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public int getFacilityID() {
        return facilityID;
    }

    @Override
    public String toString() {
        return "HappensIn{" +
                "eventID=" + eventID +
                ", careCardNumber=" + careCardNumber +
                ", facilityID=" + facilityID +
                '}';
    }
}
