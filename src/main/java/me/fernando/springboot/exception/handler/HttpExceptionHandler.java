package me.fernando.springboot.exception.handler;

import me.fernando.springboot.exception.BadRequestException;
import me.fernando.springboot.exception.details.BadRequestExceptionDetails;
import me.fernando.springboot.exception.details.ExceptionDetails;
import me.fernando.springboot.exception.details.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException exception) {

        final BadRequestExceptionDetails exceptionDetails =
                new BadRequestExceptionDetails("Bad Request Exception", LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(), exception.getMessage(), exception.getClass().getName());

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final ExceptionDetails exceptionDetails =
                new ExceptionDetails(exception.getCause().getMessage(), LocalDateTime.now(),
                        status.value(), "Verifique os erros dos campos",
                        exception.getClass().getName());

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        final String fields = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField).collect(Collectors.joining(", "));

        final String fieldsMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        final ValidationExceptionDetails exceptionDetails =
                new ValidationExceptionDetails("Bad Request Exception, Campos Inválidos", LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(), "Verifique os erros dos campos",
                        exception.getClass().getName(), fields, fieldsMessage);

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

}
