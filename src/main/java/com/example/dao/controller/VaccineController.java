package com.example.dao.controller;

import com.example.dao.domain.dto.VaccineDto;
import com.example.dao.service.VaccineService;
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
@RequestMapping(value = "/vaccines", produces = MediaType.APPLICATION_JSON_VALUE)
public class VaccineController {
  private final VaccineService vaccineService;

  @GetMapping
  public ResponseEntity<List<VaccineDto>> getAll() {
    return ResponseEntity.ok(this.vaccineService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<VaccineDto> getById(@PathVariable long id) {
    log.info("Get by id. id=" + id);
    return ResponseEntity.ok(this.vaccineService.getById(id));
  }

  @PostMapping(headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<VaccineDto> create(@RequestBody @Valid VaccineDto data) {
    log.info("Create. data=" + data);
    return new ResponseEntity<>(this.vaccineService.create(data), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<VaccineDto> update(
      @PathVariable long id, @RequestBody @Valid VaccineDto data) {
    log.info("Update. id=" + id + ", data=" + data);
    return ResponseEntity.ok(this.vaccineService.update(id, data));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteById(@PathVariable long id) {
    log.info("Delete by id. id=" + id);
    return ResponseEntity.ok(this.vaccineService.deleteById(id));
  }
}
