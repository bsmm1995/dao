package com.example.dao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
  public ReportDto(Object[] objects) {
    this.personDni = (String) objects[0];
    this.personFullNames = (String) objects[1];
    this.date = (Date) objects[2];
    this.dose = (int) objects[3];
    this.vaccineName = (String) objects[4];
    this.vaccineLot = (String) objects[5];
  }

  private String personDni;
  private String personFullNames;
  private Date date;
  private int dose;
  private String vaccineName;
  private String vaccineLot;
}
