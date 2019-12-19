package am.ith.service.exception;

public class AccountExistsException extends SocialNetworkException {
  private static final long serialVersionUID = 7587368065361068560L;

  public AccountExistsException(String message) {
    super(message);
  }

  public AccountExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
