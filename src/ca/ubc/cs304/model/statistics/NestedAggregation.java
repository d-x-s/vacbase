package ca.ubc.cs304.model.statistics;

public class NestedAggregation {
    private int careCardNumber;
    private int count;

    public NestedAggregation(int careCardNumber, int count) {
        this.careCardNumber = careCardNumber;
        this.count = count;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public int getCount() {
        return count;
    }
}
