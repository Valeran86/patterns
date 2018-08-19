package ru.sbtschool.patterns;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ru.sbtschool.patterns.dao.EmployerDAO;
import ru.sbtschool.patterns.dto.ReportDto;
import ru.sbtschool.patterns.send.MailSender;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SalaryHtmlReportNotifierTest {

    private static String HTML_REPORT =
    "<html><body><table>" +
            "<tr><th>emp_name</th><th>salary</th></tr>" +
            "<tr><td>zero point 1</td><td>0.1</td></tr>" +
            "<tr><td>zero point 2</td><td>0.2</td></tr>" +
            "<tr><td>Total</td><td>0.3</td></tr>" +
    "</table></body></html>";

    SalaryHtmlReportNotifier reportNotifier;

    Connection mockedConnection;
    PreparedStatement mockedStatement;
    ResultSet mockedResultSet;

    MailSender mockedSender;

    @Before
    public void setUp() throws SQLException {
        mockedResultSet = mock( ResultSet.class );
        when( mockedResultSet.next() )
                .thenReturn( true )
        .thenReturn( true )
        .thenReturn( false );

        when( mockedResultSet.getDouble( anyString() ) )
                .thenReturn( 0.1 )
                .thenReturn( 0.2 );

        when( mockedResultSet.getString( anyString() ) )
                .thenReturn( "zero point 1" )
                .thenReturn( "zero point 2" );

        mockedStatement = mock( PreparedStatement.class );
        when( mockedStatement.executeQuery() )
                .thenReturn( mockedResultSet );

        mockedConnection = mock( Connection.class );
        when( mockedConnection.prepareStatement( anyString() ) )
                .thenReturn( mockedStatement );

        EmployerDAO employerDAO = new EmployerDAO( mockedConnection );
        mockedSender = mock( MailSender.class );
        reportNotifier = new SalaryHtmlReportNotifier( employerDAO, mockedSender );
    }

    @Test
    public void testGenerateReport() throws MessagingException {
        reportNotifier.generateAndSendHtmlSalaryReport(
                "depart"
                ,LocalDate.now()
                ,LocalDate.now()
                ,getClass().getCanonicalName()
                ,DecimalFormatSymbols.getInstance( Locale.US )
                , "###,###,###.##"
        );

        ArgumentCaptor<ReportDto> captor = ArgumentCaptor.forClass( ReportDto.class );
        verify( mockedSender ).send( captor.capture() );

        ReportDto reportDto = captor.getValue();

        assertEquals( "Monthly department salary report", reportDto.getSubject() );
        assertEquals( true, reportDto.isHtml() );
        assertEquals( getClass().getCanonicalName(), reportDto.getRecipients() );
        assertEquals( HTML_REPORT, reportDto.getContent() );
    }
}
