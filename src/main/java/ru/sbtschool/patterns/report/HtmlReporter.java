package ru.sbtschool.patterns.report;

import ru.sbtschool.patterns.dto.ReportDepartmentResultDto;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Home on 16.08.2018.
 */
public class HtmlReporter {
    public static String toHtml( ReportDepartmentResultDto departmentResultDto ) {
        String[] columns = departmentResultDto.getColumns();

        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append( "<html><body><table><tr>");

        for ( String column : columns )
            resultingHtml.append( surroundWith( "th", column ) );
        resultingHtml.append( "</tr>" );

        for ( Object[] row : departmentResultDto.getRows() ) {
            // process each row of query results
            resultingHtml.append( "<tr>" ); // add row start tag

            for ( int i = 0; i < columns.length; i++ ) {
                resultingHtml.append( surroundWith( "td", Objects.toString( row[ i ] ) ) );
            }

            resultingHtml.append( "</tr>" ); // add row end tag
        }


        for ( Object[] total : departmentResultDto.getTotals() ) {
            resultingHtml.append( "<tr>" );
            resultingHtml.append("<td>").append(total[0]).append("</td>");
            resultingHtml.append("<td>").append(String.format( Locale.ENGLISH,"%.1f", total[1])).append("</td>");
            resultingHtml.append("</tr>");
        }
        resultingHtml.append( "</table></body></html>" );

        return resultingHtml.toString();
    }

    private static String surroundWith( String tag, String text ) {
        return "<" + tag + ">" + text + "</" + tag + ">";
    }
}
