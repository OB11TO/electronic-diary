package ru.ae.coursemodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ae.coursemodel.report.XlsTeacherReport;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherReportService {

    private final XlsTeacherReport xlsTeacherReport;

    public byte[] generateTeacherWorkReport() {
        return xlsTeacherReport.generateWorkLoadReport();
    }
}
