package com.example.demo.service;

import com.example.demo.model.Vehicle;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private static final String FILE = "cars.txt";

    // CREATE — add a new vehicle
    public void addVehicle(Vehicle v) {
        v.setVehicleId(FileUtil.generateId(FILE, "CAR"));
        FileUtil.appendLine(FILE, v.toFileString());
    }

    // READ — get all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(Vehicle.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping bad line: " + line); }
        }
        return list;
    }

    // READ — search by category
    public List<Vehicle> searchByCategory(String category) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getCategory().equalsIgnoreCase(category)) result.add(v);
        }
        return result;
    }

    // READ — find one vehicle by ID
    public Vehicle findById(String vehicleId) {
        for (Vehicle v : getAllVehicles()) {
            if (v.getVehicleId().equals(vehicleId)) return v;
        }
        return null;
    }

    // UPDATE — update vehicle by index
    public boolean updateVehicle(int index, Vehicle updated) {
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    // DELETE — delete vehicle by index
    public boolean deleteVehicle(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    // Get index of a vehicle by ID (needed for update/delete)
    public int getIndexById(String vehicleId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(vehicleId + ",")) return i;
        }
        return -1;
    }
}