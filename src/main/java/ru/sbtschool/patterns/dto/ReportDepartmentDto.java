package ru.sbtschool.patterns.dto;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Home on 16.08.2018.
 */
public class ReportDepartmentDto {
    private final String departmentId;
    private final Date dateFrom;
    private final Date dateTo;

    public ReportDepartmentDto( String departmentId, Date dateFrom, Date dateTo ) {
        this.departmentId = departmentId;
        this.dateFrom = new Date( dateFrom.getTime() );
        this.dateTo = new Date( dateTo.getTime() );
    }
    public ReportDepartmentDto( String departmentId, LocalDate dateFrom, LocalDate dateTo ) {
        this( departmentId, new Date( dateFrom.toEpochDay() ), new Date( dateTo.toEpochDay() ) );
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }
}
