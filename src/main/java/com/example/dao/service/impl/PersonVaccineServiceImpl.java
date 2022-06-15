package com.example.dao.service.impl;

import com.example.dao.domain.PersonVaccine;
import com.example.dao.domain.dto.PersonVaccineDto;
import com.example.dao.repository.PersonVaccineRepository;
import com.example.dao.service.PersonVaccineService;
import com.example.dao.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonVaccineServiceImpl implements PersonVaccineService {
  private final PersonVaccineRepository personVaccineRepository;

  @Override
  public PersonVaccineDto getById(long id) {
    Optional<PersonVaccine> optional = this.personVaccineRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    return Mapper.modelMapper().map(optional.get(), PersonVaccineDto.class);
  }

  @Override
  public List<PersonVaccineDto> getAll() {
    List<PersonVaccine> personVaccines = this.personVaccineRepository.findAll();
    return personVaccines.stream()
        .map(element -> Mapper.modelMapper().map(element, PersonVaccineDto.class))
        .toList();
  }

  @Override
  public PersonVaccineDto create(PersonVaccineDto data) {
    PersonVaccine personVaccine = Mapper.modelMapper().map(data, PersonVaccine.class);
    return Mapper.modelMapper()
        .map(this.personVaccineRepository.save(personVaccine), PersonVaccineDto.class);
  }

  @Override
  public PersonVaccineDto update(long id, PersonVaccineDto data) {
    Optional<PersonVaccine> optional = this.personVaccineRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    optional.get().setPersonId(data.getPersonId());
    optional.get().setVaccineId(data.getVaccineId());
    optional.get().setDate(data.getDate());
    optional.get().setDose(data.getDose());
    PersonVaccine personVaccine = this.personVaccineRepository.save(optional.get());
    return Mapper.modelMapper().map(personVaccine, PersonVaccineDto.class);
  }

  @Override
  public long deleteById(long id) {
    if (this.personVaccineRepository.findById(id).isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    this.personVaccineRepository.deleteById(id);
    return id;
  }
}
