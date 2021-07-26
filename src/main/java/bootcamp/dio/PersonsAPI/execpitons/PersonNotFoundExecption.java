package bootcamp.dio.PersonsAPI.execpitons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExecption extends Exception {
    public PersonNotFoundExecption(Long id) {
        super("Person not found id: "+id);
    }
}
