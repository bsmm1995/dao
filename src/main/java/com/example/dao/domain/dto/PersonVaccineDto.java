package com.example.dao.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Data
public class PersonVaccineDto {
  private long id;

  @NotNull(message = "El ID de la vacuna no puede ser nulo.")
  @Min(value = 1, message = "El ID de la vacuna no puede ser menor o igual que cero.")
  private long vaccineId;

  @NotNull(message = "El ID de la persona no puede ser nulo.")
  @Min(value = 1, message = "El ID de la persona no puede ser menor o igual que cero.")
  private long personId;

  @PastOrPresent(message = "La fecha no puede ser mayor a la fecha actual.")
  private Date date;

  @Range(
      min = 1,
      max = 4,
      message = "El número de dosis debe ser mayor que cero y menor o igual que 4.")
  private int dose;
}
