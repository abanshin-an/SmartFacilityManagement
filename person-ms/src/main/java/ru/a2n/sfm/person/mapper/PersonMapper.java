package ru.a2n.sfm.person.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.dto.PersonFullDto;
import ru.a2n.sfm.person.dto.PersonListItemDto;
import ru.a2n.sfm.person.model.Person;

@Mapper(componentModel = "spring")
@SuppressWarnings("java:S6813")
public abstract class PersonMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    public abstract Person toPerson(PersonCreateDto personCreateDto);

    @AfterMapping
    public void calledWithPersonCreateDtoAndTargetType(PersonCreateDto dto, @MappingTarget Person person) {
        person.setPassword(passwordEncoder.encode(dto.password()));
    }

    public abstract PersonFullDto toPersonFullDto(Person person);

    public abstract PersonListItemDto toPersonListItemDto(Person person);
}
