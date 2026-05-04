package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "booking/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("today", LocalDate.now().toString());
        return "booking/add";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute Booking booking, Model model) {
        boolean available = bookingService.isVehicleAvailable(
                booking.getVehicleId(), booking.getStartDate(), booking.getEndDate());
        if (!available) {
            model.addAttribute("booking", booking);
            model.addAttribute("today", LocalDate.now().toString());
            model.addAttribute("error", "Vehicle " + booking.getVehicleId() +
                    " is not available for the selected dates.");
            return "booking/add";
        }
        bookingService.addBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("booking", bookingService.findById(id));
        model.addAttribute("index", bookingService.getIndexById(id));
        return "booking/edit";
    }

    @PostMapping("/edit")
    public String updateBooking(@RequestParam int index, @ModelAttribute Booking booking) {
        bookingService.updateBooking(index, booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(bookingService.getIndexById(id));
        return "redirect:/bookings";
    }
}