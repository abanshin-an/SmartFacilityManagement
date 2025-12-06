package ru.a2n.sfm.person.client;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.a2n.sfm.person.dto.PersonFullDto;
import ru.a2n.sfm.person.dto.PersonListItemDto;

@FeignClient(value = "person", url = "https://person/")
public interface PersonClient {

    @GetMapping(value = "/api/person")
    List<PersonListItemDto> getAllPerson();

    @GetMapping(value = "api/person/{id}", produces = "application/json")
    @Cacheable(cacheNames = "person", key = "#id")
    PersonFullDto getById(@PathVariable("id") String id);
}
