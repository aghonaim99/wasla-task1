package com.example.waslatask1.Exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    private Map<String, Object> createErrorMap(LocalDateTime time, String errorCode, String errorMsg){
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", time);
        errorBody.put("code", errorCode);
        errorBody.put("message", errorMsg);

        return errorBody;
    }
    @ExceptionHandler(InvalidCategoryIDException.class)
    public ResponseEntity<Object> handleInvalidCategoryException(InvalidCategoryIDException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-1", "Category does not exist");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPostIDException.class)
    public ResponseEntity<Object> handleInvalidCategoryException(InvalidPostIDException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-2", "Post does not exist");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostAlreadyExistsException.class)
    public ResponseEntity<Object> handlePostAlreadyExistsException(PostAlreadyExistsException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-3", "Post already exists");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidLanguageException.class)
    public ResponseEntity<Object> handleInvalidLangException(InvalidLanguageException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-4", "Invalid language");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InaccessibleFileException.class)
    public ResponseEntity<Object> handleInaccessibleFileException(InaccessibleFileException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-5", "Can't access file specified");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CantParseNodeException.class)
    public ResponseEntity<Object> handleCantParseNodeException(CantParseNodeException ex, WebRequest request)
    {
        Map<String, Object> body = createErrorMap(LocalDateTime.now(), "-6", "Can't parse node");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
