package main.java.enums;

public enum InputType {
    RAW("raw"),
    SHUFFLED("shuffled"),
    SORTED("sorted"),
    ALL("all");

    private final String label;
    InputType(String label) { this.label = label; }
    public String getLabel() { return label; }
}