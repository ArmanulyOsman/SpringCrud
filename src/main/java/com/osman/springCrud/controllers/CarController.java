package com.osman.springCrud.controllers;

import com.osman.springCrud.services.CarService;
import com.osman.springCrud.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("cars", carService.getAllCars());
        return "cars/index";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable int id, Model model){
        model.addAttribute("car", carService.show(id));
        return "cars/show";
    }

    @GetMapping("/new-car")
    public String newCar(@ModelAttribute("car") Car car){
        return "cars/new";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute("car") Car car){
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String updateCar(@PathVariable int id, Model model){
        model.addAttribute("car", carService.show(id));
        return "cars/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@ModelAttribute("car") Car car, @PathVariable("id") int id){
        carService.updateCar(id, car);
        return "redirect:/cars";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@ModelAttribute("car") Car car, @PathVariable("id") int id){
        carService.delete(id);
        return "redirect:/cars";
    }

    @PostMapping("/deleteAll")
    public String deleteAll(){
        carService.deleteAll();
        return "redirect:/cars";
    }
}
