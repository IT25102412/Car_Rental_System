package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String listPayments(@RequestParam(required = false) String userId, Model model) {
        if (userId != null && !userId.isEmpty()) {
            model.addAttribute("payments", paymentService.getByUserId(userId));
            model.addAttribute("searchUserId", userId);
        } else {
            model.addAttribute("payments", paymentService.getAllPayments());
            model.addAttribute("searchUserId", "");
        }
        return "payment/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("today", LocalDate.now().toString());
        return "payment/add";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute Payment payment) {
        paymentService.addPayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        model.addAttribute("index", paymentService.getIndexById(id));
        return "payment/edit";
    }

    @PostMapping("/edit")
    public String updatePayment(@RequestParam int index, @ModelAttribute Payment payment) {
        paymentService.updatePayment(index, payment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable String id) {
        paymentService.deletePayment(paymentService.getIndexById(id));
        return "redirect:/payments";
    }
}