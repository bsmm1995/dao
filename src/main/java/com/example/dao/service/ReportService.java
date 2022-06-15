package com.example.dao.service;

import com.example.dao.domain.dto.ReportDto;

import java.util.Date;
import java.util.List;

public interface ReportService {
  List<ReportDto> getByDateRange(Date startDate, Date endDate);
}
