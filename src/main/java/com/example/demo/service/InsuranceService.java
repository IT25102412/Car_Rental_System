package com.example.demo.service;

import com.example.demo.model.Insurance;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceService {

    private static final String FILE = "insurance.txt";

    public void addInsurance(Insurance ins) {
        ins.setInsuranceId(FileUtil.generateId(FILE, "INS"));
        FileUtil.appendLine(FILE, ins.toFileString());
    }

    public List<Insurance> getAllPolicies() {
        List<Insurance> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(Insurance.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping: " + line); }
        }
        return list;
    }

    public Insurance findById(String insuranceId) {
        for (Insurance ins : getAllPolicies()) {
            if (ins.getInsuranceId().equals(insuranceId)) return ins;
        }
        return null;
    }

    // Verify if a vehicle has active coverage on a specific date
    public boolean hasActiveCoverage(String vehicleId, String date) {
        for (Insurance ins : getAllPolicies()) {
            if (ins.getVehicleId().equals(vehicleId)
                    && ins.getStatus().equals("Active")
                    && ins.isActiveOn(date)) {
                return true;
            }
        }
        return false;
    }

    public boolean updateInsurance(int index, Insurance updated) {
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    public boolean deleteInsurance(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    public int getIndexById(String insuranceId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(insuranceId + ",")) return i;
        }
        return -1;
    }
}