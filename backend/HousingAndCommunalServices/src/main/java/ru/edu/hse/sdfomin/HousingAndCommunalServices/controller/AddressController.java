package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Address;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.House;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.HouseManager;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.AddressRepo;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.HouseManagerRepo;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.HouseRepo;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class AddressController {

    private final AddressRepo addressRepo;

    private final HouseRepo houseRepo;

    private final HouseManagerRepo houseManagerRepo;

    public AddressController(AddressRepo addressRepo, HouseRepo houseRepo, HouseManagerRepo houseManagerRepo) {
        this.addressRepo = addressRepo;
        this.houseRepo = houseRepo;
        this.houseManagerRepo = houseManagerRepo;
    }

    @GetMapping("/address")
    public String main(Map<String, Object> model) {
        model.put("housemanagers", houseManagerRepo.findAll());
        return "address";
    }

    @PostMapping("/address/add")
    public String add(@RequestParam String city,
                      @RequestParam String street,
                      @RequestParam String houseNumber,
                      @RequestParam HouseManager houseManager,
                      Map<String, Object> model) {
        Address address = new Address(city, street, houseNumber);
        addressRepo.save(address);
        House house = new House(address, new ArrayList<>(), houseManager);
        houseRepo.save(house);

        model.put("message", "Successfully added");
        model.put("housemanagers", houseManagerRepo.findAll());
        return "address";
    }
}
