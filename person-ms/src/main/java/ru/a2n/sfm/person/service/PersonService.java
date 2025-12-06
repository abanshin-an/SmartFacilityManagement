package ru.a2n.sfm.person.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.a2n.sfm.person.dto.ChangePasswordDto;
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.dto.PersonFullDto;
import ru.a2n.sfm.person.dto.PersonListItemDto;
import ru.a2n.sfm.person.mapper.PersonMapper;
import ru.a2n.sfm.person.model.Person;
import ru.a2n.sfm.person.repository.PersonRepository;

@SuppressWarnings({"squid:S4449", "java:S1068"})
@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository repository, PasswordEncoder passwordEncoder, PersonMapper personMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.personMapper = personMapper;
    }

    @Transactional(readOnly = true)
    @Cacheable("persons")
    public List<PersonListItemDto> getAllPersons(String login, int limit) {
        try {
            logger.info("getAllPersons {}", login);
            List<PersonListItemDto> entities;
            if (login == null) {
                if (limit > 1000 || limit < 0) {
                    limit = 1000;
                }
                entities = repository.findAllPerson(limit);
            } else {
                entities = repository.findByLoginContaining(login).stream()
                        .map(personMapper::toPersonListItemDto)
                        .toList();
            }
            logger.info("found {}", entities.size());
            return entities;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return List.of();
        }
    }

    @Cacheable("persons")
    public Optional<PersonFullDto> getPersonById(String id) {
        Person person = repository.findById(id).orElse(null);
        return mapFullDto(person);
    }

    @CacheEvict(value = "persons", allEntries = true)
    public boolean deletePerson(String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
        return true;
    }

    @Transactional
    @CacheEvict(value = "persons", allEntries = true)
    public Optional<PersonFullDto> createPerson(PersonCreateDto person) {
        try {
            logger.info("create person with login {}", person.login());
            Person personToSave = personMapper.toPerson(person);
            Person createdPerson = repository.save(personToSave);
            return mapFullDto(createdPerson);
        } catch (Exception e) {
            logger.error("creat person exception {}", e.getMessage());
        }
        return Optional.empty();
    }

    @Transactional
    @CacheEvict(value = "persons", allEntries = true)
    public Optional<PersonFullDto> changePassword(ChangePasswordDto changePasswordDto) {
        try {
            logger.info("change password person with login {}", changePasswordDto.login());
            Person person = repository.findById(changePasswordDto.id()).orElseThrow();
            if (passwordEncoder.matches(changePasswordDto.oldPassword(), person.getPassword())) {
                person.setPassword(passwordEncoder.encode(changePasswordDto.newPassword()));
                Person changedPerson = repository.save(person);
                return mapFullDto(changedPerson);
            }
        } catch (Exception e) {
            logger.error("changePassword exception {}", e.getMessage());
        }
        return Optional.empty();
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void deleteFakePerson() {
        logger.info("deleteFakePerson");
        repository.deleteFakePerson();
    }

    private Optional<PersonFullDto> mapFullDto(Person person) {
        return Optional.of(personMapper.toPersonFullDto(person));
    }
}
