package br.com.gabrielcaio.academic_management_system.controllers.handler;

import br.com.gabrielcaio.academic_management_system.controllers.error.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage err = new ErrorMessage(Instant.now(),status.value(), e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorMessage> handlerEntityExists(EntityExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage err = new ErrorMessage(Instant.now(),status.value(), e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ErrorMessage> handlerDataBase(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage err = new ErrorMessage(Instant.now(),status.value(), e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handlerMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(),status.value(), "Dados invalidos",request.getRequestURI());

        e.getFieldErrors().forEach(fieldError -> {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccesDeniedException(AccessDeniedException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorMessage err = new ErrorMessage(Instant.now(), status.value(), "Acesso Negado.", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}

