package ca.ubc.cs304.model.patient;

import java.sql.Date;

/**
 * The intent for this class is to update/store information about a single patient account
 */
public class PatientAccount {
    private final int careCardNumber;
    private String fullName;
    private Date date;
    private String username;

    public PatientAccount(int careCardNumber, String fullName, Date date, String username) {
        this.careCardNumber = careCardNumber;
        this.fullName = fullName;
        this.date = date;
        this.username = username;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }
}
