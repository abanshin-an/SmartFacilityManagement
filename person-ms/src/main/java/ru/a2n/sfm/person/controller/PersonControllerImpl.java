package ru.a2n.sfm.person.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.person.dto.ChangePasswordDto;
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.dto.PersonFullDto;
import ru.a2n.sfm.person.dto.PersonListItemDto;
import ru.a2n.sfm.person.service.PersonService;

@SuppressWarnings("squid:S4449")
@RestController
public class PersonControllerImpl implements PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonControllerImpl.class);

    private final PersonService service;
    private final PersonService personService;

    public PersonControllerImpl(PersonService service, PersonService personService) {
        this.service = service;
        this.personService = personService;
    }

    @Override
    public ResponseEntity<List<PersonListItemDto>> getAllPerson(String name, Integer limit) {
        try {
            if (limit == null) {
                limit = 10000;
            }
            var entities = service.getAllPersons(name, limit);
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.status(OK).body(entities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @Override
    public ResponseEntity<PersonFullDto> getById(String id) {
        Optional<PersonFullDto> person = service.getPersonById(id);
        return person.map(value -> ResponseEntity.status(OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<PersonFullDto> changePassword(ChangePasswordDto changePasswordDto) {
        Optional<PersonFullDto> person = service.changePassword(changePasswordDto);
        return person.map(value -> ResponseEntity.status(OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Void> deletePerson(String id) {
        if (personService.deletePerson(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<PersonFullDto> createPerson(PersonCreateDto person) {
        logger.info("create person with login {}", person.login());
        Optional<PersonFullDto> savedPerson = service.createPerson(person);
        return savedPerson
                .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
