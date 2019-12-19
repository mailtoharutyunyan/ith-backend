package am.ith.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SocialNetworkException extends RuntimeException {

  private static final long serialVersionUID = 8322041481342023481L;
  private final String description;

  SocialNetworkException(String message) {
    super(message);
    description = null;
  }

  public SocialNetworkException(String message, String description) {
    super(message);
    this.description = description;
  }

  SocialNetworkException(String message, Throwable cause) {
    super(message, cause);
    description = null;
  }
}
