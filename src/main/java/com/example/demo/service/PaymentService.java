package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private static final String FILE = "payments.txt";

    public void addPayment(Payment p) {
        p.setPaymentId(FileUtil.generateId(FILE, "PAY"));
        p.setTotalAmount(p.calculateFee());
        FileUtil.appendLine(FILE, p.toFileString());
    }

    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(Payment.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping: " + line); }
        }
        return list;
    }

    public List<Payment> getByUserId(String userId) {
        List<Payment> result = new ArrayList<>();
        for (Payment p : getAllPayments()) {
            if (p.getUserId().equals(userId)) result.add(p);
        }
        return result;
    }

    public Payment findById(String paymentId) {
        for (Payment p : getAllPayments()) {
            if (p.getPaymentId().equals(paymentId)) return p;
        }
        return null;
    }

    public boolean updatePayment(int index, Payment updated) {
        updated.setTotalAmount(updated.calculateFee());
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    public boolean deletePayment(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    public int getIndexById(String paymentId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(paymentId + ",")) return i;
        }
        return -1;
    }
}