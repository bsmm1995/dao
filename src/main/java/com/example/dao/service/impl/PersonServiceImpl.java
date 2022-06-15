package com.example.dao.service.impl;

import com.example.dao.domain.Person;
import com.example.dao.domain.dto.PersonDto;
import com.example.dao.repository.PersonRepository;
import com.example.dao.service.PersonService;
import com.example.dao.util.Mapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
  private final PersonRepository personRepository;

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public PersonDto getById(long id) {
    Optional<Person> optional = this.personRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    return Mapper.modelMapper().map(optional.get(), PersonDto.class);
  }

  @Override
  public List<PersonDto> getAll() {
    List<Person> personList = this.personRepository.findAll();
    return personList.stream()
        .map(element -> Mapper.modelMapper().map(element, PersonDto.class))
        .toList();
  }

  @Override
  public PersonDto create(PersonDto data) {
    Person person = Mapper.modelMapper().map(data, Person.class);
    return Mapper.modelMapper().map(this.personRepository.save(person), PersonDto.class);
  }

  @Override
  public PersonDto update(long id, PersonDto data) {
    Optional<Person> optional = this.personRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    optional.get().setDni(data.getDni());
    optional.get().setName(data.getName());
    optional.get().setLastname(data.getLastname());

    Person person = this.personRepository.save(optional.get());
    return Mapper.modelMapper().map(person, PersonDto.class);
  }

  @Override
  public long deleteById(long id) {
    if (this.personRepository.findById(id).isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    this.personRepository.deleteById(id);
    return id;
  }
}
