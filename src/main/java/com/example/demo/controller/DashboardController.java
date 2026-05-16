package com.example.demo.controller;

import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("vehicleCount",   FileUtil.countLines("cars.txt"));
        model.addAttribute("userCount",      FileUtil.countLines("users.txt"));
        model.addAttribute("bookingCount",   FileUtil.countLines("bookings.txt"));
        model.addAttribute("paymentCount",   FileUtil.countLines("payments.txt"));
        model.addAttribute("returnCount",    FileUtil.countLines("returns.txt"));
        model.addAttribute("insuranceCount", FileUtil.countLines("insurance.txt"));
        return "index";
    }
}