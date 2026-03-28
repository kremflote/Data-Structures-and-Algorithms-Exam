package main.java.records;

public record Wine(
        double fixedAcidity,
        double volatileAcidity,
        double citricAcid,
        double residualSugar,
        double chlorides,
        double freeSulfurDioxide,
        double totalSulfurDioxide,
        double density,
        double pH,
        double sulphates,
        double alcohol,
        int quality,
        WineType type
) {
    public enum WineType {
        RED, WHITE
    }
}