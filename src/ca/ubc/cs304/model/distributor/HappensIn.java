package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about the relationship between
 * VaccineEvent and Facility
 */
public class HappensIn {
    private final int eventID;
    private final String facilityName;

    public HappensIn(int eventID, String facilityName) {
        this.eventID = eventID;
        this.facilityName = facilityName;
    }

    public int getEventID() {
        return eventID;
    }

    public String getFacilityName() {
        return facilityName;
    }
}
