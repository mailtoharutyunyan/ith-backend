package am.ith.service.exception;

public class LevelNotFountException extends Exception {

    public LevelNotFountException() {}

    public LevelNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelNotFountException(Throwable cause) {
        super(cause);
    }

    public LevelNotFountException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LevelNotFountException(String course_not_found) {}
}
