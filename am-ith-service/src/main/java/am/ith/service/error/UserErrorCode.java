package am.ith.service.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND),

  USER_USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT);

  private HttpStatus status;

  @Override
  public String getName() {
    return name();
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }
}
