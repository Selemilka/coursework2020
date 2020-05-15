package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.HouseManager;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.HouseManagerRepo;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class HouseManagerController {

    private final HouseManagerRepo houseManagerRepo;

    public HouseManagerController(HouseManagerRepo houseManagerRepo) {
        this.houseManagerRepo = houseManagerRepo;
    }

    @GetMapping("/housemanager")
    public String main(Map<String, Object> model) {
        return "housemanager";
    }

    @PostMapping("/housemanager/add")
    public String add(@RequestParam String name,
                      Map<String, Object> model) {
        HouseManager houseManager = new HouseManager(new ArrayList<>(), new ArrayList<>(), name);
        houseManagerRepo.save(houseManager);
        model.put("message", "Successfully added");
        return "housemanager";
    }
}
