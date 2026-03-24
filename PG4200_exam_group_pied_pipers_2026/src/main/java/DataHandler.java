package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {

    private DataHandler() {}

    private static ArrayList<Wine> loadWines(String filePath, Wine.WineType type) {
        ArrayList<Wine> wines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }

                String[] parts = line.split(";");
                if (parts.length < 12) continue;

                double alcohol = Double.parseDouble(parts[10]);
                if (wines.stream().anyMatch(w -> w.alcohol() == alcohol)) continue;

                wines.add(new Wine(
                        Double.parseDouble(parts[0]),
                        Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]),
                        Double.parseDouble(parts[6]),
                        Double.parseDouble(parts[7]),
                        Double.parseDouble(parts[8]),
                        Double.parseDouble(parts[9]),
                        alcohol,
                        Integer.parseInt(parts[11].trim()),
                        type
                ));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
        }
        return wines;
    }

    public static ArrayList<Wine> loadAllWines(String redPath, String whitePath) {
        ArrayList<Wine> all = new ArrayList<>();
        all.addAll(loadWines(redPath, Wine.WineType.RED));
        all.addAll(loadWines(whitePath, Wine.WineType.WHITE));
        return all;
    }
}