package com.example.lifewaydemo.dto;

import com.example.lifewaydemo.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    Person PersonDtoToPerson(PersonDto personDto);
    PersonDto PersonToPersonDto(Person person);
}
