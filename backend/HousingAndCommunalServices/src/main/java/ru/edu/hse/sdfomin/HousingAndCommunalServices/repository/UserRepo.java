package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
