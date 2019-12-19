package am.ith.service.exception;

public class ContentNotFoundException extends SocialNetworkException {
  private static final long serialVersionUID = 7674527485147476348L;

  public ContentNotFoundException(String message) {
    super(message);
  }

  public ContentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
