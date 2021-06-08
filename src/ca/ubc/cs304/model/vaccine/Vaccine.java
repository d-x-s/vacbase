package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single vaccine
 */
public class Vaccine {
	private final String vacName;
	private String type;
	private double dosage;

	public Vaccine(String vacName, String type, double dosage) {
		this.vacName = vacName;
		this.type = type;
		this.dosage = dosage;
	}

	public String getVacName() {
		return vacName;
	}

	public String getType() {
		return type;
	}

	public double getDosage() {
		return dosage;
	}
}
