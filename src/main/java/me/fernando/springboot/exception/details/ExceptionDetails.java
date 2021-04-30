package me.fernando.springboot.exception.details;

import java.time.LocalDateTime;

public class ExceptionDetails {

    private final String title;
    private final LocalDateTime timestamp;
    private final int status;
    private final String details;
    private final String developerMessage;

    public ExceptionDetails(String title, LocalDateTime timestamp, int status, String details, String developerMessage) {
        this.title = title;
        this.timestamp = timestamp;
        this.status = status;
        this.details = details;
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
