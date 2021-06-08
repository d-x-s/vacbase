package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about a single facility
 */
public class Facility {
    private final String facilityName;
    private String address;

    public Facility(String facilityName, String address) {
        this.facilityName = facilityName;
        this.address = address;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getAddress() {
        return address;
    }
}
