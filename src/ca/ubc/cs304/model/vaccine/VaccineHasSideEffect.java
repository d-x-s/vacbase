package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about the relationship between
 * Vaccine and sideEffect
 */
public class VaccineHasSideEffect {
    private final String vacName;
    private final String sideEffectName;

    public VaccineHasSideEffect(String vacName, String sideEffectName) {
        this.vacName = vacName;
        this.sideEffectName = sideEffectName;
    }

    public String getVacName() {
        return vacName;
    }

    public String getSideEffectName() {
        return sideEffectName;
    }
}
