package ca.ubc.cs304.model.vaccine;

public class ChickenPox extends Vaccine {
    private boolean previousShinglesVacRequired;

    public ChickenPox(int vacID, String vacName, String type, double dosage, boolean previousShinglesVacRequired) {
        super(vacID, vacName, type, dosage);
        this.previousShinglesVacRequired = previousShinglesVacRequired;
    }

    public boolean isPreviousShinglesVacRequired() {
        return previousShinglesVacRequired;
    }
}
