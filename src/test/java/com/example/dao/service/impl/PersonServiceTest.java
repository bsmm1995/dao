package com.example.dao.service.impl;

import com.example.dao.domain.Person;
import com.example.dao.domain.dto.PersonDto;
import com.example.dao.repository.PersonRepository;
import com.example.dao.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  private PersonService personService;
  private PersonRepository personRepositoryMock;

  @BeforeEach
  void setUp() {
    personRepositoryMock = Mockito.mock(PersonRepository.class);
    personService = new PersonServiceImpl(personRepositoryMock);
  }

  @Test
  void getById() {
    long id = 1;
    Person person = new Person();
    person.setId(id);
    person.setName("BSMM");
    person.setDni("123456789");

    when(personRepositoryMock.findById(id)).thenReturn(Optional.of(person));

    PersonDto personDto = personService.getById(id);

    assertNotNull(personDto);
    assertEquals(id, personDto.getId());
  }
}
