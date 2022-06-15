package com.example.dao.service;

import com.example.dao.domain.dto.PersonDto;

import java.util.List;

public interface PersonService {
  PersonDto getById(long id);

  List<PersonDto> getAll();

  PersonDto create(PersonDto data);

  PersonDto update(long id, PersonDto data);

  long deleteById(long id);
}
