package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.model.Vehicle;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private VehicleService vehicleService;
    @Autowired private BookingService bookingService;
    @Autowired private UserService userService;
    @Autowired private com.example.demo.service.PaymentService paymentService;

    // Check if customer is logged in
    private boolean isCustomer(HttpSession session) {
        return session.getAttribute("loggedIn") != null &&
                !session.getAttribute("userType").equals("Admin");
    }

    // Customer home page
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("availableCars", vehicleService.searchByCategory("").size());
        model.addAttribute("totalCars", vehicleService.getAllVehicles().size());
        return "customer/home";
    }

    // Browse available cars
    @GetMapping("/cars")
    public String browseCars(@RequestParam(required = false) String category,
                             HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        List<Vehicle> cars;
        if (category != null && !category.isEmpty()) {
            cars = vehicleService.searchByCategory(category);
        } else {
            cars = vehicleService.getAllVehicles();
        }
        cars = cars.stream()
                .filter(v -> v.getStatus().equals("Available"))
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        model.addAttribute("selectedCategory", category != null ? category : "");
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/cars";
    }

    // Show booking form
    @GetMapping("/book/{vehicleId}")
    public String showBookForm(@PathVariable String vehicleId,
                               HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        Vehicle vehicle = vehicleService.findById(vehicleId);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/book";
    }

    // Submit booking
    @PostMapping("/book")
    public String submitBooking(@RequestParam String vehicleId,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";

        String userId = (String) session.getAttribute("userId");
        boolean available = bookingService.isVehicleAvailable(vehicleId, startDate, endDate);

        if (!available) {
            Vehicle vehicle = vehicleService.findById(vehicleId);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("today", LocalDate.now().toString());
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("error", "This car is not available for selected dates.");
            return "customer/book";
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setVehicleId(vehicleId);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setStatus("Pending");
        bookingService.addBooking(booking);

        return "redirect:/customer/mybookings?booked=true";
    }

    // My bookings
    @GetMapping("/mybookings")
    public String myBookings(HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        String userId = (String) session.getAttribute("userId");
        List<Booking> myBookings = bookingService.getAllBookings().stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
        model.addAttribute("bookings", myBookings);
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/mybookings";
    }

    // My profile
    @GetMapping("/profile")
    public String myProfile(HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        String userId = (String) session.getAttribute("userId");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/profile";
    }

    // Update profile
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user,
                                HttpSession session) {
        if (!isCustomer(session)) return "redirect:/login";
        String userId = (String) session.getAttribute("userId");
        int index = userService.getIndexById(userId);
        user.setUserId(userId);
        userService.updateUser(index, user);
        return "redirect:/customer/profile";
    }

    // Show payment page — only if booking is Confirmed
    @GetMapping("/payment/{bookingId}")
    public String showPayment(@PathVariable String bookingId,
                              HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";

        Booking booking = bookingService.findById(bookingId);

        if (booking.getStatus().equals("Pending")) {
            return "redirect:/customer/mybookings?error=pending";
        }
        if (booking.getStatus().equals("Rejected")) {
            return "redirect:/customer/mybookings?error=rejected";
        }

        Vehicle vehicle = vehicleService.findById(booking.getVehicleId());

        java.time.LocalDate start = java.time.LocalDate.parse(booking.getStartDate());
        java.time.LocalDate end   = java.time.LocalDate.parse(booking.getEndDate());
        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        if (days < 1) days = 1;
        double total = days * vehicle.getDailyRate();

        model.addAttribute("booking", booking);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("days", days);
        model.addAttribute("total", total);
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/payment";
    }

    // Handle payment submission
    @PostMapping("/payment")
    public String processPayment(@RequestParam String bookingId,
                                 @RequestParam String cardName,
                                 @RequestParam String cardNumber,
                                 @RequestParam String expiry,
                                 @RequestParam String cvv,
                                 HttpSession session) {
        if (!isCustomer(session)) return "redirect:/login";

        Booking booking = bookingService.findById(bookingId);
        Vehicle vehicle = vehicleService.findById(booking.getVehicleId());

        java.time.LocalDate start = java.time.LocalDate.parse(booking.getStartDate());
        java.time.LocalDate end   = java.time.LocalDate.parse(booking.getEndDate());
        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        if (days < 1) days = 1;
        double total = days * vehicle.getDailyRate();

        com.example.demo.model.Payment payment = new com.example.demo.model.Payment();
        payment.setUserId(booking.getUserId());
        payment.setVehicleId(booking.getVehicleId());
        payment.setRentalType("Daily");
        payment.setRentalDays((int) days);
        payment.setRatePerDay(vehicle.getDailyRate());
        payment.setTotalAmount(total);
        payment.setStatus("Paid");
        payment.setPaymentDate(java.time.LocalDate.now().toString());
        paymentService.addPayment(payment);

        return "redirect:/customer/payment-success?bookingId=" + bookingId + "&total=" + total;
    }

    // Payment success page
    @GetMapping("/payment-success")
    public String paymentSuccess(@RequestParam String bookingId,
                                 @RequestParam double total,
                                 HttpSession session, Model model) {
        if (!isCustomer(session)) return "redirect:/login";
        Booking booking = bookingService.findById(bookingId);
        model.addAttribute("booking", booking);
        model.addAttribute("total", total);
        model.addAttribute("username", session.getAttribute("username"));
        return "customer/payment-success";
    }
}