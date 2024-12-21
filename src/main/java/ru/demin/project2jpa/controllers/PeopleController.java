package ru.demin.project2jpa.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleServicel) {
        this.peopleService = peopleServicel;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.showAll());
        System.out.println("111111111111111");
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "people/new";
        else {peopleService.save(person);
        return "redirect:/people";}
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.showById(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.showById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "people/edit";
        else {peopleService.update(id, person);
        return "redirect:/people";}
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        peopleService.deleteById(id);
        return "redirect:/people";
    }
}
