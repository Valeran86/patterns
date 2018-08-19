package ru.sbtschool.patterns.dto;

/**
 * Created by Home on 16.08.2018.
 */
public class ReportDto {
    private final String subject;
    private final Boolean isHtml;
    private final String content;
    private final String recipients;

    public ReportDto( String subject, Boolean isHtml, String content, String recipients ) {
        this.subject = subject;
        this.isHtml = isHtml;
        this.content = content;
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean isHtml() {
        return isHtml;
    }

    public String getContent() {
        return content;
    }

    public String getRecipients() {
        return recipients;
    }
}
