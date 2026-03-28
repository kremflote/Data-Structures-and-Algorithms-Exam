package main.java.enums;

public enum OperationLabel {
    COMPARISONS("comparisons"),
    SWAPS("swaps"),
    MERGE_OPERATIONS("merge operations");

    private final String label;

    OperationLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
