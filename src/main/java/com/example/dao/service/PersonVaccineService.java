package com.example.dao.service;

import com.example.dao.domain.dto.PersonVaccineDto;

import java.util.List;

public interface PersonVaccineService {
  PersonVaccineDto getById(long id);

  List<PersonVaccineDto> getAll();

  PersonVaccineDto create(PersonVaccineDto data);

  PersonVaccineDto update(long id, PersonVaccineDto data);

  long deleteById(long id);
}
