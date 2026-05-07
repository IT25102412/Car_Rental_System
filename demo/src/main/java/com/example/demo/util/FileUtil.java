package com.example.demo.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String DATA_DIR = "data/";

    public static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(DATA_DIR + filename);
        if (!Files.exists(path)) {
            createFileIfNotExists(filename);
            return lines;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading: " + filename + " - " + e.getMessage());
        }
        return lines;
    }

    public static void writeLines(String filename, List<String> lines) {
        createDataDirectory();
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Paths.get(DATA_DIR + filename).toFile(), false))) {
            for (String line : lines) { writer.write(line); writer.newLine(); }
        } catch (IOException e) {
            System.err.println("Error writing: " + filename + " - " + e.getMessage());
        }
    }

    public static void appendLine(String filename, String line) {
        createFileIfNotExists(filename);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Paths.get(DATA_DIR + filename).toFile(), true))) {
            writer.write(line); writer.newLine();
        } catch (IOException e) {
            System.err.println("Error appending: " + filename + " - " + e.getMessage());
        }
    }

    public static boolean deleteLine(String filename, int lineIndex) {
        List<String> lines = readLines(filename);
        if (lineIndex < 0 || lineIndex >= lines.size()) return false;
        lines.remove(lineIndex);
        writeLines(filename, lines);
        return true;
    }

    public static boolean updateLine(String filename, int lineIndex, String updatedLine) {
        List<String> lines = readLines(filename);
        if (lineIndex < 0 || lineIndex >= lines.size()) return false;
        lines.set(lineIndex, updatedLine);
        writeLines(filename, lines);
        return true;
    }

    public static int countLines(String filename) {
        return readLines(filename).size();
    }

    public static String generateId(String filename, String prefix) {
        return prefix + String.format("%03d", countLines(filename) + 1);
    }

    private static void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    private static void createFileIfNotExists(String filename) {
        createDataDirectory();
        File file = new File(DATA_DIR + filename);
        try { if (!file.exists()) file.createNewFile(); }
        catch (IOException e) { System.err.println("Error creating: " + filename); }
    }
}