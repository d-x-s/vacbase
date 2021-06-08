package ca.ubc.cs304.model.patient;

import java.sql.Date;

/**
 * The intent for this class is to update/store information about a single patient's age bracket lookup
 * (A PatientAccount BCNF)
 */
public class AgeBracketLookup {
    private final Date DOB;
    private String ageBracket;

    public AgeBracketLookup(Date dob, String ageBracket) {
        DOB = dob;
        this.ageBracket = ageBracket;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getAgeBracket() {
        return ageBracket;
    }
}
