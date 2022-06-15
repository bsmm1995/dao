package com.example.dao.service.impl;

import com.example.dao.domain.dto.ReportDto;
import com.example.dao.repository.DaoRepository;
import com.example.dao.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final DaoRepository daoRepository;

  @Override
  public List<ReportDto> getByDateRange(Date startDate, Date endDate) {
    List<Object[]> objects = this.daoRepository.getReport(startDate, endDate);
    List<ReportDto> reportDtos = new ArrayList<>(0);
    for (Object[] object : objects) {
      reportDtos.add(new ReportDto(object));
    }
    return reportDtos;
  }
}
