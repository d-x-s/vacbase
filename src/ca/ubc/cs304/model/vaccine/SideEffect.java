package ca.ubc.cs304.model.vaccine;
/**
 * The intent for this class is to update/store information about a single side effect
 */
public class SideEffect {
    private final String sideEffectName;
    private String treatment;
    private int duration;

    public SideEffect(String sideEffectName, String treatment, int duration) {
        this.sideEffectName = sideEffectName;
        this.treatment = treatment;
        this.duration = duration;
    }

    public String getSideEffectName() {
        return sideEffectName;
    }

    public String getTreatment() {
        return treatment;
    }

    public int getDuration() {
        return duration;
    }
}
