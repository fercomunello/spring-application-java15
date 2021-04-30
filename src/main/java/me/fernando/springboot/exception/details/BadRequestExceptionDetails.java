package me.fernando.springboot.exception.details;

import java.time.LocalDateTime;

public class BadRequestExceptionDetails extends ExceptionDetails {

    public BadRequestExceptionDetails(String title, LocalDateTime timestamp, int status,
                                      String details, String developerMessage) {
        super(title, timestamp, status, details, developerMessage);
    }
}
