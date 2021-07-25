package bootcamp.dio.PersonsAPI.service;

import bootcamp.dio.PersonsAPI.dto.response;
import bootcamp.dio.PersonsAPI.entity.Person;
import bootcamp.dio.PersonsAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public response createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return response
                .builder()
                .message("Create person with ID "+savedPerson.getId())
                .build();
    }
}
