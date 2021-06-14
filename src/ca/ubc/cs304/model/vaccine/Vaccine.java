package ca.ubc.cs304.model.vaccine;

/**
 * The intent for this class is to update/store information about a single vaccine
 */
public class Vaccine {
	private final int vacID;
	private String vacName;
	private String type;
	private double dosage;

	public Vaccine(int vacID, String vacName, String type, double dosage) {
		this.vacID = vacID;
		this.vacName = vacName;
		this.type = type;
		this.dosage = dosage;
	}

	public int getVacID() {
		return vacID;
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

	@Override
	public String toString() {
		return "Vaccine{" +
				"vacID=" + vacID +
				", vacName='" + vacName + '\'' +
				", type='" + type + '\'' +
				", dosage=" + dosage +
				'}';
	}
}
