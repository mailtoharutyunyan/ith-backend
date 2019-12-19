package am.ith.service.exception;

public class ContentRequiredException extends SocialNetworkException {
  private static final long serialVersionUID = 7497973504966509418L;

  public ContentRequiredException(String message) {
    super(message);
  }

  public ContentRequiredException(String message, Throwable cause) {
    super(message, cause);
  }
}
