package ru.sbtschool.patterns;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbtschool.patterns.dao.EmployerDAO;
import ru.sbtschool.patterns.dto.ReportDepartmentResultDto;
import ru.sbtschool.patterns.dto.ReportDepartmentDto;
import ru.sbtschool.patterns.dto.ReportDto;
import ru.sbtschool.patterns.report.HtmlReporter;
import ru.sbtschool.patterns.send.MailSender;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    final EmployerDAO dao;
    final MailSender mailSender;

    public SalaryHtmlReportNotifier( EmployerDAO dao, MailSender mailSender ) {
        this.dao = dao;
        this.mailSender = mailSender;
    }

    public void generateAndSendHtmlSalaryReport( String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients ) {
        ReportDepartmentResultDto reportDepartmentResultDto =
                generateSalaryReport(
                        new ReportDepartmentDto( departmentId, dateFrom, dateTo )
                );

        sendHtmlSalaryReport( reportDepartmentResultDto, recipients );
    }

    public ReportDepartmentResultDto generateSalaryReport( ReportDepartmentDto reportDepartmentDto ) {
        try {
            return dao.getSalaryByDepartment( reportDepartmentDto );
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public void sendHtmlSalaryReport( ReportDepartmentResultDto salaryReport, String recipients ) {
        try {
            ReportDto reportDto = new ReportDto(
                    "Monthly department salary report"
                    , true
                    , HtmlReporter.toHtml( salaryReport )
                    , recipients
            );


            mailSender.send( reportDto );
        } catch ( MessagingException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
