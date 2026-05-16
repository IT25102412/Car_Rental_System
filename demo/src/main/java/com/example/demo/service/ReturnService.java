package com.example.demo.service;

import com.example.demo.model.ReturnReport;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnService {

    private static final String FILE = "returns.txt";

    public void addReturn(ReturnReport r) {
        r.setReturnId(FileUtil.generateId(FILE, "RET"));
        // Auto calculate fine before saving
        r.setLateFine(r.calculateFine());
        FileUtil.appendLine(FILE, r.toFileString());
    }

    public List<ReturnReport> getAllReturns() {
        List<ReturnReport> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(ReturnReport.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping: " + line); }
        }
        return list;
    }

    public ReturnReport findById(String returnId) {
        for (ReturnReport r : getAllReturns()) {
            if (r.getReturnId().equals(returnId)) return r;
        }
        return null;
    }

    public boolean updateReturn(int index, ReturnReport updated) {
        updated.setLateFine(updated.calculateFine());
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    public boolean deleteReturn(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    public int getIndexById(String returnId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(returnId + ",")) return i;
        }
        return -1;
    }
}