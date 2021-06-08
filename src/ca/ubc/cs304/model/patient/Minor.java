package ca.ubc.cs304.model.patient;

/**
 * The intent for this class is to update/store information about a single minor patient
 */
public class Minor {
    private final int careCardNumber;
    private int minorCardNumber;

    public Minor(int careCardNumber, int minorCardNumber) {
        this.careCardNumber = careCardNumber;
        this.minorCardNumber = minorCardNumber;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public int getMinorCardNumber() {
        return minorCardNumber;
    }
}
