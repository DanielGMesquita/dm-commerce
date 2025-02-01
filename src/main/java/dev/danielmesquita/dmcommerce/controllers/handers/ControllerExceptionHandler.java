package dev.danielmesquita.dmcommerce.controllers.handers;

import dev.danielmesquita.dmcommerce.dtos.CustomError;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(Instant.now(), 404, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }
}
