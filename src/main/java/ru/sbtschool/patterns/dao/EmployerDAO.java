package ru.sbtschool.patterns.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbtschool.patterns.dto.ReportDepartmentResultDto;
import ru.sbtschool.patterns.dto.ReportDepartmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Home on 16.08.2018.
 */
public class EmployerDAO {

    public static final String EMP_NAME = "emp_name";
    public static final String SALARY = "salary";

    public static final String EMP_SALARY_SQL =
            "select emp.id as emp_id, emp.name as " + EMP_NAME + ", sum(salary) as " + SALARY +
                    " from employee emp left join salary_payments sp on emp.id = sp.employee_id " +
                    "where emp.department_id = ?" +
                    "  and sp.date >= ?" +
                    "  and sp.date <= ? " +
                    "group by emp.id, emp.name";

    @Autowired
    Connection connection;

    public ReportDepartmentResultDto getSalaryByDepartment( ReportDepartmentDto reportDepartmentDto ) throws SQLException {
        // prepare statement with sql
        PreparedStatement ps = connection.prepareStatement( EMP_SALARY_SQL );
        // inject parameters to sql
        ps.setString( 0, reportDepartmentDto.getDepartmentId() );
        ps.setDate( 1, reportDepartmentDto.getDateFrom() );
        ps.setDate( 2, reportDepartmentDto.getDateTo() );
        // execute query and get the results

        ReportDepartmentResultDto resultDto = new ReportDepartmentResultDto( EMP_NAME, SALARY );
        ResultSet resultSet = ps.executeQuery();
        double totals = 0;
        while ( resultSet.next() ) {
            Object[] row = new Object[] {
                    resultSet.getString( EMP_NAME )
                    ,resultSet.getDouble( SALARY )
            };
            resultDto.appendRow( row );

            totals += resultSet.getDouble( SALARY ); // add salary to totals
        }
        resultDto.appendTotal( new Object[] { "Total", totals } );

        return resultDto;
    }
}
