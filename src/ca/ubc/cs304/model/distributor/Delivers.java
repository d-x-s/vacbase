package ca.ubc.cs304.model.distributor;

import java.sql.Date;

/**
 * The intent for this class is to update/store information about the relationship between
 * Vaccine and Distributor
 */
public class Delivers {
    private final String distributorName;
    private final String facilityName;
    private int orderID;
    private int quantity;
    private Date timeOfDelivery;

    public Delivers(String distributorName, String facilityName, int orderID, int quantity, Date timeOfDelivery) {
        this.distributorName = distributorName;
        this.facilityName = facilityName;
        this.orderID = orderID;
        this.quantity = quantity;
        this.timeOfDelivery = timeOfDelivery;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getTimeOfDelivery() {
        return timeOfDelivery;
    }
}
