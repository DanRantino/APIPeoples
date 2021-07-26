package bootcamp.dio.PersonsAPI.service;

import bootcamp.dio.PersonsAPI.dto.request.PersonDTO;
import bootcamp.dio.PersonsAPI.dto.response.MessageResponseDTO;
import bootcamp.dio.PersonsAPI.entity.Person;
import bootcamp.dio.PersonsAPI.execpitons.PersonNotFoundExecption;
import bootcamp.dio.PersonsAPI.mapper.PersonMapper;
import bootcamp.dio.PersonsAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTACE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Create person with ID "+ savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> todos = personRepository.findAll();
        return todos
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }
    private Person verifyExists(Long id)throws PersonNotFoundExecption {
        Person person = personRepository.findById(id).orElseThrow(()->new PersonNotFoundExecption(id));
        return person;
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExecption {
        Person person = verifyExists(id);
        return personMapper.toDto(person);
    }

    public void deleteOne(Long id)throws PersonNotFoundExecption {
        verifyExists(id);
        personRepository.deleteById(id);
    }
}
