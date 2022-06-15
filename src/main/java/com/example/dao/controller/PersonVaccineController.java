package com.example.dao.controller;

import com.example.dao.domain.dto.PersonVaccineDto;
import com.example.dao.service.PersonVaccineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/persons-vaccine", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonVaccineController {
  private final PersonVaccineService vaccineService;

  @GetMapping
  public ResponseEntity<List<PersonVaccineDto>> getAll() {
    return ResponseEntity.ok(this.vaccineService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonVaccineDto> getById(@PathVariable long id) {
    log.info("Get by id. id=" + id);
    return ResponseEntity.ok(this.vaccineService.getById(id));
  }

  @PostMapping(headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<PersonVaccineDto> create(@RequestBody @Valid PersonVaccineDto data) {
    log.info("Create. data=" + data);
    return new ResponseEntity<>(this.vaccineService.create(data), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<PersonVaccineDto> update(
      @PathVariable long id, @RequestBody @Valid PersonVaccineDto data) {
    log.info("Update. id=" + id + ", data=" + data);
    return ResponseEntity.ok(this.vaccineService.update(id, data));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteById(@PathVariable long id) {
    log.info("Delete by id. id=" + id);
    return ResponseEntity.ok(this.vaccineService.deleteById(id));
  }
}
