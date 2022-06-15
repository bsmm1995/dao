package com.example.dao.controller;

import com.example.dao.domain.dto.PersonDto;
import com.example.dao.service.PersonService;
import com.example.dao.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonServiceImpl personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<PersonDto>> getAll() {
    return ResponseEntity.ok(this.personService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonDto> getById(@PathVariable long id) {
    log.info("Get by id. id=" + id);
    return ResponseEntity.ok(this.personService.getById(id));
  }

  @PostMapping(headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto data) {
    log.info("Create. data=" + data);
    return new ResponseEntity<>(this.personService.create(data), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<PersonDto> update(
      @PathVariable long id, @RequestBody @Valid PersonDto data) {
    log.info("Update. id=" + id + ", data=" + data);
    return ResponseEntity.ok(this.personService.update(id, data));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteById(@PathVariable long id) {
    log.info("Delete by id. id=" + id);
    return ResponseEntity.ok(this.personService.deleteById(id));
  }
}
