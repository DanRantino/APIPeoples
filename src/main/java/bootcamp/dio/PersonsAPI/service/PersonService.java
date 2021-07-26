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
        return createMessage(savedPerson.getId(), "Create person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> todos = personRepository.findAll();
        return todos
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExecption {
        // Método único para verificar existente retirbabdi oessias
        Person person = verifyExists(id);
        return personMapper.toDto(person);
    }

    public void deleteOne(Long id)throws PersonNotFoundExecption {
        verifyExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateOne(Long id, PersonDTO personDTO) throws PersonNotFoundExecption {
        // Método único para verificar existente
        verifyExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        // Adicionado Id dos parametros da requisição para não precisar informar no body
        personToUpdate.setId(id);
        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessage(updatedPerson.getId(), "Updated person with ID ");
    }

    private MessageResponseDTO createMessage(Long id, String Message) {
        return MessageResponseDTO
                .builder()
                .message(Message + id)
                .build();
    }
    private Person verifyExists(Long id)throws PersonNotFoundExecption {
        Person person = personRepository.findById(id).orElseThrow(()->new PersonNotFoundExecption(id));
        return person;
    }

}
