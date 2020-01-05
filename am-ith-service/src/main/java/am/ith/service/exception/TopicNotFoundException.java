package am.ith.service.exception;

public class TopicNotFoundException extends Exception {

    public TopicNotFoundException() {}

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }

    public TopicNotFoundException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TopicNotFoundException(String course_not_found) {}
}
