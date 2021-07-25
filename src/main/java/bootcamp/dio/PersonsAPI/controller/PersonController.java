package bootcamp.dio.PersonsAPI.controller;

import bootcamp.dio.PersonsAPI.dto.response;
import bootcamp.dio.PersonsAPI.entity.Person;
import bootcamp.dio.PersonsAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public response createPerson(@RequestBody Person person)
    {
        return personService.createPerson(person);
    }
}
