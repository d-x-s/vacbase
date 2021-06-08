package ca.ubc.cs304.model.distributor;

/**
 * The intent for this class is to update/store information about a single distributor
 */
public class Distributor {
    private final String distributorName;
    private int distributorPhoneNum;
    private String distributorEmail;

    public Distributor(String distributorName, int distributorPhoneNum, String distributorEmail) {
        this.distributorName = distributorName;
        this.distributorPhoneNum = distributorPhoneNum;
        this.distributorEmail = distributorEmail;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public int getDistributorPhoneNum() {
        return distributorPhoneNum;
    }

    public String getDistributorEmail() {
        return distributorEmail;
    }
}
