package com.example.dao.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PersonDto {
  private long id;

  @NotEmpty(message = "El DNI no puede ser nulo ni vacío.")
  private String dni;

  @NotEmpty(message = "El nombre no puede ser nulo ni vacío.")
  private String name;

  @NotEmpty(message = "El apellido no puede ser nulo ni vacío.")
  private String lastname;
}
