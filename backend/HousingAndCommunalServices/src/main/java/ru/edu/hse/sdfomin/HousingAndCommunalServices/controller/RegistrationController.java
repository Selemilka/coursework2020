package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Role;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.User;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User username = userRepo.findByUsername(user.getUsername());
        if (username != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
