package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.report.XlsTeacherReport;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherReportService {

    private final XlsTeacherReport xlsTeacherReport;

    public byte[] generateTeacherWorkReport() {
        log.info("Report generation");
        return xlsTeacherReport.generateWorkLoadReport();
    }
}
