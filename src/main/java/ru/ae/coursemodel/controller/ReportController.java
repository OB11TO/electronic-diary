package ru.ae.coursemodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ae.coursemodel.service.TeacherReportService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final TeacherReportService teacherReportService;

    @GetMapping
    public ResponseEntity<byte[]> generateTeacherWorkLoadReport() {
        var httpHeaders = new HttpHeaders();
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        var currentDateTime = dateTimeFormatter.format(LocalDateTime.now());
        httpHeaders.add("Content-Disposition", "attachment; " +
                "filename=Report-" + currentDateTime + ".xlsx");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(teacherReportService.generateTeacherWorkReport());
    }
}
