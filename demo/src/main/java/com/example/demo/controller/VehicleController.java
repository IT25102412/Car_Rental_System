package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // READ — show all vehicles
    @GetMapping
    public String listVehicles(@RequestParam(required = false) String category, Model model) {
        if (category != null && !category.isEmpty()) {
            model.addAttribute("vehicles", vehicleService.searchByCategory(category));
            model.addAttribute("selectedCategory", category);
        } else {
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("selectedCategory", "");
        }
        return "vehicle/list";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/add";
    }

    // CREATE — save new vehicle
    @PostMapping("/add")
    public String addVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return "redirect:/vehicles";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Vehicle vehicle = vehicleService.findById(id);
        int index = vehicleService.getIndexById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("index", index);
        return "vehicle/edit";
    }

    // UPDATE — save edited vehicle
    @PostMapping("/edit")
    public String updateVehicle(@RequestParam int index,
                                @ModelAttribute Vehicle vehicle) {
        vehicleService.updateVehicle(index, vehicle);
        return "redirect:/vehicles";
    }

    // DELETE — remove vehicle
    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable String id) {
        int index = vehicleService.getIndexById(id);
        vehicleService.deleteVehicle(index);
        return "redirect:/vehicles";
    }
}