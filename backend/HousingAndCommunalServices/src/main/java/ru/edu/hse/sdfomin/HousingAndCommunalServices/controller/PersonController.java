package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.PersonRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private final PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("/person")
    public String main(Map<String, Object> model) {
        model.put("persons", personRepo.findAll());
        return "person";
    }

    @PostMapping("/person/add")
    public String add(@RequestParam String givenName,
                      @RequestParam String familyName,
                      @RequestParam String email,
                      @RequestParam String mobilePhone,
                      @RequestParam String googleId,
                      Map<String, Object> model) {
        Person person = new Person(givenName, familyName, email, mobilePhone, googleId, new ArrayList<>(), new ArrayList<>());
        personRepo.save(person);
        model.put("persons", personRepo.findAll());
        return "person";
    }
}
