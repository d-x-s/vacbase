package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about a single distributor's courier
 * (A Distributor BCNF)
 */
public class Courier {
    private final String distributorName;
    private String courierName;

    public Courier(String distributorName, String courierName) {
        this.distributorName = distributorName;
        this.courierName = courierName;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public String getCourierName() {
        return courierName;
    }
}
