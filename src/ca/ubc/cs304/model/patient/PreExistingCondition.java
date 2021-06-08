package ca.ubc.cs304.model.patient;

/**
 * The intent for this class is to update/store information about a single patient's pre-existing medical condition
 */
public class PreExistingCondition {
    private final int careCardNumber;
    private String condition;

    public PreExistingCondition(int careCardNumber, String condition) {
        this.careCardNumber = careCardNumber;
        this.condition = condition;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public String getCondition() {
        return condition;
    }
}
