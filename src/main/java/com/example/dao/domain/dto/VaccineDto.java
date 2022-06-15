package com.example.dao.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VaccineDto {
  private Long id;

  @NotEmpty(message = "El nombre no puede ser nulo ni vacío.")
  private String name;

  @NotEmpty(message = "El lote no puede ser nulo ni vacío.")
  private String lot;
}
