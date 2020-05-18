package ru.edu.hse.sdfomin.HousingAndCommunalServices.api;

import org.springframework.web.bind.annotation.*;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.PersonRepo;

@RestController
@RequestMapping("api/person")
public class PersonRestController {

    private final PersonRepo personRepo;

    public PersonRestController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") String id) {
        return personRepo.findByGoogleId(id);
    }

    @GetMapping
    public Iterable<Person> getPersons() {
        return personRepo.findAll();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        Person prevPerson = personRepo.findByGoogleId(person.getGoogleId());
        if (prevPerson != null) {
            personRepo.delete(prevPerson);
        }
        personRepo.save(person);
    }
}
