package ca.ubc.cs304.model;

import java.sql.Date;

public class PatientAccountModel {
    private final int CareCardNumber;
    private final String FullName;
    private final Date Date;
    private final String UserName;

    public PatientAccountModel(int CareCardNumber, String FullName, Date Date, String UserName) {
        this.CareCardNumber = CareCardNumber;
        this.FullName = FullName;
        this.Date = Date;
        this.UserName = UserName;
    }

    public int getCareCardNumber() { return CareCardNumber; }
    public String getFullName() { return FullName; }
    public Date getDate() { return Date; }
    public String getUserName() { return UserName; }

}
