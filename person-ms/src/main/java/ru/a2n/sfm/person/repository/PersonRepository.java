package ru.a2n.sfm.person.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.a2n.sfm.person.dto.PersonListItemDto;
import ru.a2n.sfm.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByLoginContaining(String login);

    @Query(value = "SELECT id, person_login FROM person LIMIT :limit", nativeQuery = true)
    List<PersonListItemDto> findAllPerson(int limit);
}
