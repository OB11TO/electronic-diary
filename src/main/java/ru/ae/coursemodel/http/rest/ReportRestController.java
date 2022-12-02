package ru.ae.coursemodel.http.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Report", description = "отчет")
public class ReportRestController {

    private final TeacherReportService teacherReportService;

    @Operation(summary = "Получить отчет по загрузке преподавательского состава")
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
