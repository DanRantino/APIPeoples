package bootcamp.dio.PersonsAPI.controller;

import bootcamp.dio.PersonsAPI.dto.request.PersonDTO;
import bootcamp.dio.PersonsAPI.dto.response.MessageResponseDTO;
import bootcamp.dio.PersonsAPI.execpitons.PersonNotFoundExecption;
import bootcamp.dio.PersonsAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO)
    {
        return personService.createPerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findId(@PathVariable Long id) throws PersonNotFoundExecption {
        return personService.findById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable Long id) throws PersonNotFoundExecption {
        personService.deleteOne(id);
    }
}
