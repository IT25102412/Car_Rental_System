package com.example.demo.controller;

import com.example.demo.model.Insurance;
import com.example.demo.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping
    public String listPolicies(Model model) {
        model.addAttribute("policies", insuranceService.getAllPolicies());
        return "insurance/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("insurance", new Insurance());
        model.addAttribute("today", LocalDate.now().toString());
        return "insurance/add";
    }

    @PostMapping("/add")
    public String addInsurance(@ModelAttribute Insurance insurance) {
        insuranceService.addInsurance(insurance);
        return "redirect:/insurance";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("insurance", insuranceService.findById(id));
        model.addAttribute("index", insuranceService.getIndexById(id));
        return "insurance/edit";
    }

    @PostMapping("/edit")
    public String updateInsurance(@RequestParam int index,
                                  @ModelAttribute Insurance insurance) {
        insuranceService.updateInsurance(index, insurance);
        return "redirect:/insurance";
    }

    @GetMapping("/delete/{id}")
    public String deleteInsurance(@PathVariable String id) {
        insuranceService.deleteInsurance(insuranceService.getIndexById(id));
        return "redirect:/insurance";
    }
}