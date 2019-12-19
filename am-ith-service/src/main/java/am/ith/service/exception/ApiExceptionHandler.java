package am.ith.service.exception;

import am.ith.service.entity.ErrorResponseMy;
import am.ith.service.entity.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccountExistsException.class)
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    ErrorResponseMy response = new ErrorResponseMy(HttpStatus.CONFLICT, ex.getMessage());
    log.error("Getting AccountExistsException : {}", ex.getMessage());
    return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(BadCredentialsException.class)
  protected ResponseEntity<Object> handleBadCredentials(RuntimeException ex, WebRequest request) {
    ErrorResponseMy response =
        new ErrorResponseMy(HttpStatus.UNAUTHORIZED, ResponseStatus.ACCOUNT_NOT_FOUND.getMessage());
    log.error("Getting BadCredentialsException : {}", ex.getMessage());
    return handleExceptionInternal(
        ex, response, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(ContentNotFoundException.class)
  protected ResponseEntity<Object> handleContentNotFound(RuntimeException ex, WebRequest request) {
    ErrorResponseMy response =
        new ErrorResponseMy(HttpStatus.NOT_FOUND, ResponseStatus.CONTENT_NOT_FOUND.getMessage());
    log.error("Getting ContentNotFoundException : {}", ex.getMessage());
    return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(ContentRequiredException.class)
  protected ResponseEntity<Object> handleContentRequired(RuntimeException ex, WebRequest request) {
    ErrorResponseMy response =
        new ErrorResponseMy(
            HttpStatus.UNPROCESSABLE_ENTITY, ResponseStatus.ACCOUNT_NOT_FOUND.getMessage());
    log.error("Getting ContentRequiredException : {}", ex.getMessage());
    return handleExceptionInternal(
        ex, response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

  @ExceptionHandler(SocialNetworkException.class)
  public void handleSocialNetworkException(HttpServletResponse response, SocialNetworkException ex)
      throws IOException {
    log.error("Getting SocialNetworkException : {}", ex.getMessage());
  }

  @NotNull
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      @NotNull HttpHeaders headers,
      @NotNull HttpStatus status,
      @NotNull WebRequest request) {
    log.error("Getting MethodArgumentNotValidException : {}", ex.getMessage());
    return new ResponseEntity<>(ex.getBindingResult().getModel(), headers, status);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeny(HttpServletResponse response) throws IOException {
    log.error("Getting AccessDeniedException : {Access denied}");
    response.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }
}
