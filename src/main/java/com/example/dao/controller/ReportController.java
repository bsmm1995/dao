package com.example.dao.controller;

import com.example.dao.domain.dto.ReportDto;
import com.example.dao.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @GetMapping("/by-date-range")
  public ResponseEntity<List<ReportDto>> getByDateRange(
      @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
      @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
    log.info("Get by date range. startDate=" + startDate + ", endDate=" + endDate);
    return ResponseEntity.ok(this.reportService.getByDateRange(startDate, endDate));
  }
}
