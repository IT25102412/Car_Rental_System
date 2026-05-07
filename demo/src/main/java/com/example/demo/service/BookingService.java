package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private static final String FILE = "bookings.txt";

    public void addBooking(Booking b) {
        b.setBookingId(FileUtil.generateId(FILE, "BKG"));
        FileUtil.appendLine(FILE, b.toFileString());
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(Booking.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping: " + line); }
        }
        return list;
    }

    public Booking findById(String bookingId) {
        for (Booking b : getAllBookings()) {
            if (b.getBookingId().equals(bookingId)) return b;
        }
        return null;
    }

    // Check if a vehicle is available for given dates
    public boolean isVehicleAvailable(String vehicleId, String start, String end) {
        for (Booking b : getAllBookings()) {
            if (b.getVehicleId().equals(vehicleId)
                    && b.getStatus().equals("Confirmed")
                    && !b.isAvailable(start, end)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateBooking(int index, Booking updated) {
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    public boolean deleteBooking(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    public int getIndexById(String bookingId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(bookingId + ",")) return i;
        }
        return -1;

    }
}