package ca.ubc.cs304.model.vaccine;
/**
 * The intent for this class is to update/store information about a single nurse
 */
public class Nurse {
    private final int nurseID;
    private String nurseName;

    public Nurse(int nurseID, String nurseName) {
        this.nurseID = nurseID;
        this.nurseName = nurseName;
    }

    public int getNurseID() {
        return nurseID;
    }

    public String getNurseName() {
        return nurseName;
    }
}
