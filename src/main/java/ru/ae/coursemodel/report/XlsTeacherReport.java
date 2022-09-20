package ru.ae.coursemodel.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.ae.coursemodel.dto.TeacherStatisticDto;
import ru.ae.coursemodel.repository.TeacherRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class XlsTeacherReport {

    private final TeacherRepository teacherRepository;

    public byte[] generateWorkLoadReport() {
        var report = teacherRepository.findAllTeacherStatistics();

        try (var workbook = new XSSFWorkbook();
             var outputStream = new ByteArrayOutputStream()) {

            var sheet = workbook.createSheet("Отчёт");
            var listHeader = List.of("ФИО профессора", "Количество студентов", "Средняя успеваемость студентов");

            setHeader(workbook, sheet, listHeader);
            setRow(report, sheet);

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            log.error("Ошибка в формировании отчета");
            e.printStackTrace();
            return null;
        }
    }

    private void setHeader(XSSFWorkbook workbook, XSSFSheet sheet, List<String> listHeader) {
        var font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
        var cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        //Header
        var header = sheet.createRow(0);

        for (int i = 0; i < listHeader.size(); i++) {
            var cell = header.createCell(i);
            cell.setCellValue(listHeader.get(i));
            cell.setCellStyle(cellStyle);
            sheet.autoSizeColumn(i);
        }
    }

    private void setRow(List<TeacherStatisticDto> report, XSSFSheet sheet) {
        //Row
        for (int i = 0; i < report.size(); i++) {
            var teacherStatisticDto = report.get(i);
            var row = sheet.createRow(i + 1);

            row.createCell(0)
                    .setCellValue(teacherStatisticDto.getTeacherName());
            row.createCell(1)
                    .setCellValue(teacherStatisticDto.getStudentCounts());
            row.createCell(2)
                    .setCellValue(teacherStatisticDto.getAvgCurrentGradeAllCourse());
        }
    }
}
