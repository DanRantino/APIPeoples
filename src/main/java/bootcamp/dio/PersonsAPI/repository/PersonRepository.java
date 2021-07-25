package bootcamp.dio.PersonsAPI.repository;

import bootcamp.dio.PersonsAPI.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
