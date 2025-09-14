package dev.danielmesquita.dmcommerce.controllers.handers;

import dev.danielmesquita.dmcommerce.dtos.CustomError;
import dev.danielmesquita.dmcommerce.dtos.ValidationError;
import dev.danielmesquita.dmcommerce.services.exceptions.DatabaseException;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(
      ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error =
        new CustomError(Instant.now(), 404, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<CustomError> databaseException(
      DatabaseException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomError error =
        new CustomError(Instant.now(), 400, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomError> methodArgumentNotValid(
      MethodArgumentNotValidException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError error =
        new ValidationError(Instant.now(), 422, e.getMessage(), request.getRequestURI());
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    for (FieldError f : fieldErrors) {
      error.addError(f.getField(), f.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomError> accessDenied(
        AccessDeniedException e, HttpServletRequest request) {
      HttpStatus status = HttpStatus.FORBIDDEN;
      CustomError error =
          new CustomError(Instant.now(), 403, e.getMessage(), request.getRequestURI());
      return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<CustomError> authenticationException(
          AuthenticationException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    CustomError error =
            new CustomError(Instant.now(), 401, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }
}
