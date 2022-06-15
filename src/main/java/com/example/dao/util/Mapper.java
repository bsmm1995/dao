package com.example.dao.util;

import org.modelmapper.ModelMapper;

public class Mapper {
  private Mapper() {
    throw new IllegalStateException("Utility class");
  }

  public static ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
