package am.ith.service.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ValidationError implements ErrorCode {
  VALIDATION_ERROR(HttpStatus.BAD_REQUEST);

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
