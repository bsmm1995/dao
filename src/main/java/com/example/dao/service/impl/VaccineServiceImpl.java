package com.example.dao.service.impl;

import com.example.dao.domain.Vaccine;
import com.example.dao.domain.dto.VaccineDto;
import com.example.dao.repository.VaccineRepository;
import com.example.dao.service.VaccineService;
import com.example.dao.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VaccineServiceImpl implements VaccineService {
  private final VaccineRepository vaccineRepository;

  @Override
  public VaccineDto getById(long id) {
    Optional<Vaccine> optional = this.vaccineRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    return Mapper.modelMapper().map(optional.get(), VaccineDto.class);
  }

  @Override
  public List<VaccineDto> getAll() {
    List<Vaccine> vaccines = this.vaccineRepository.findAll();
    return vaccines.stream()
        .map(element -> Mapper.modelMapper().map(element, VaccineDto.class))
        .toList();
  }

  @Override
  public VaccineDto create(VaccineDto data) {
    Vaccine vaccine = Mapper.modelMapper().map(data, Vaccine.class);
    return Mapper.modelMapper().map(this.vaccineRepository.save(vaccine), VaccineDto.class);
  }

  @Override
  public VaccineDto update(long id, VaccineDto data) {
    Optional<Vaccine> optional = this.vaccineRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    optional.get().setName(data.getName());
    optional.get().setLot(data.getLot());

    Vaccine vaccine = this.vaccineRepository.save(optional.get());
    return Mapper.modelMapper().map(vaccine, VaccineDto.class);
  }

  @Override
  public long deleteById(long id) {
    if (this.vaccineRepository.findById(id).isEmpty()) {
      throw new NotFoundException("No existe el registro con id " + id);
    }
    this.vaccineRepository.deleteById(id);
    return id;
  }
}
