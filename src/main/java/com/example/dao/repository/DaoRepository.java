package com.example.dao.repository;

import com.example.dao.domain.Person;
import com.example.dao.domain.dto.PersonDto;

import java.util.Date;
import java.util.List;

public interface DaoRepository {
  List<Person> findAll();

  List<Person> findAllCriteria();

  List<PersonDto> findAllCriteriaDto();

  List<Object[]> getReport(Date startDate, Date endDate);
}
