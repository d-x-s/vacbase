package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single vaccine's type and method of injection
 * (A Vaccine 3NF)
 */
public class VaccTypeMOI {
    private final String type;
    private String methodOfInjection;

    public VaccTypeMOI(String type, String methodOfInjection) {
        this.type = type;
        this.methodOfInjection = methodOfInjection;
    }

    public String getType() {
        return type;
    }

    public String getMethodOfInjection() {
        return methodOfInjection;
    }
}
