package com.example.demo.controller;

import com.example.demo.model.ReturnReport;
import com.example.demo.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping
    public String listReturns(Model model) {
        model.addAttribute("returns", returnService.getAllReturns());
        return "returnreport/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("returnReport", new ReturnReport());
        model.addAttribute("today", LocalDate.now().toString());
        return "returnreport/add";
    }

    @PostMapping("/add")
    public String addReturn(@ModelAttribute ReturnReport returnReport) {
        returnService.addReturn(returnReport);
        return "redirect:/returns";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("returnReport", returnService.findById(id));
        model.addAttribute("index", returnService.getIndexById(id));
        return "returnreport/edit";
    }

    @PostMapping("/edit")
    public String updateReturn(@RequestParam int index,
                               @ModelAttribute ReturnReport returnReport) {
        returnService.updateReturn(index, returnReport);
        return "redirect:/returns";
    }

    @GetMapping("/delete/{id}")
    public String deleteReturn(@PathVariable String id) {
        returnService.deleteReturn(returnService.getIndexById(id));
        return "redirect:/returns";
    }
}