package ca.ubc.cs304.model.patient;

import ca.ubc.cs304.model.distributor.HappensIn;
import ca.ubc.cs304.model.vaccine.AdministeredVaccGivenToPatient;
import ca.ubc.cs304.model.vaccine.Include;
import ca.ubc.cs304.model.vaccine.Nurse;

import java.sql.Date;

/**
 * SQL: COMMAND
 * SELECT VaccineRecord.CareCardNumber, VaccineRecord.ID, VaccineRecord.EventID,
 *        AdministeredVaccineGivenToPatient.NurseID, AdministeredVaccineGivenToPatient.VacDate,
 *        Include.VacName, HappensIn.FacilityName, Nurse.NurseName
 * FROM VaccineRecord
 * INNER JOIN AdministeredVaccineGivenToPatient
 *     ON VaccineRecord.eventID = AdministeredVaccineGivenToPatient.EventID and
 *        VaccineRecord.CareCardNumber = AdministeredVaccineGivenToPatient.CareCardNumber
 * INNER JOIN Include
 *     ON AdministeredVaccineGivenToPatient.eventID = Include.EventID
 * INNER JOIN HappensIn
 *     ON  AdministeredVaccineGivenToPatient.EventID = HappensIn.EventID
 * INNER JOIN NURSE
 *     ON AdministeredVaccineGivenToPatient.NurseID = Nurse.NurseID;
 * Where vaccineRecord.CareCardNumber = {User input}
 */
public class VaccineRecordAggregation {
    private int careCardNumber;
    private int id;
    private int eventId;
    private int nurseID;
    private int vacID;
    private int facilityID;
    private Date vacDate;
    private String vacName;
    private String facilityName;
    private String nurseName;

    public VaccineRecordAggregation(int careCardNumber, int id, int eventId, int nurseID, int vacID, int facilityID,
                                    Date vacDate, String vacName, String facilityName, String nurseName) {
        this.careCardNumber = careCardNumber;
        this.id = id;
        this.eventId = eventId;
        this.nurseID = nurseID;
        this.vacDate = vacDate;
        this.vacName = vacName;
        this.facilityName = facilityName;
        this.nurseName = nurseName;
        this.vacID = vacID;
        this.facilityID = facilityID;
    }

    public VaccineRecordAggregation() {
        this.careCardNumber = 0;
        this.id = 0;
        this.eventId = 0;
        this.nurseID = 0;
        this.vacDate = new java.sql.Date(System.currentTimeMillis());
        this.vacName = "";
        this.facilityName  = "";
        this.nurseName = "";
        this.vacID = 0;
        this.facilityID = 0;
    }

    public AdministeredVaccGivenToPatient makeAdministeredVaccGivenToPatient() {
        return new AdministeredVaccGivenToPatient(eventId, nurseID, careCardNumber, vacDate);
    }

    public Include makeInclude() {
        return new Include(eventId, careCardNumber, vacID);
    }

    public VaccineRecord makeVaccineRecord() {
        return new VaccineRecord(careCardNumber, id, eventId);
    }

    public HappensIn makeHappensIn() {
        return new HappensIn(eventId, careCardNumber, facilityID);
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public void setCareCardNumber(int careCardNumber) {
        this.careCardNumber = careCardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getNurseID() {
        return nurseID;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public Date getVacDate() {
        return vacDate;
    }

    public void setVacDate(Date vacDate) {
        this.vacDate = vacDate;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    @Override
    public String toString() {
        return "VaccineRecordAggregation{" +
                "careCardNumber=" + careCardNumber +
                ", id=" + id +
                ", eventId=" + eventId +
                ", nurseID=" + nurseID +
                ", vacID=" + vacID +
                ", facilityID=" + facilityID +
                ", vacDate=" + vacDate +
                ", vacName='" + vacName + '\'' +
                ", facilityName='" + facilityName + '\'' +
                ", nurseName='" + nurseName + '\'' +
                '}';
    }
}
