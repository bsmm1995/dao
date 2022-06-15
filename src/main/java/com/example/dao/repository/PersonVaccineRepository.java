package com.example.dao.repository;

import com.example.dao.domain.PersonVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonVaccineRepository extends JpaRepository<PersonVaccine, Long> {}
