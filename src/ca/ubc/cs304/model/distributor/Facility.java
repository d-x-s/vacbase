package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about a single facility
 */
public class Facility {
    private final int facilityID;
    private String facilityName;
    private String address;

    public Facility(int facilityID, String facilityName, String address) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.address = address;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facilityID=" + facilityID +
                ", facilityName='" + facilityName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
