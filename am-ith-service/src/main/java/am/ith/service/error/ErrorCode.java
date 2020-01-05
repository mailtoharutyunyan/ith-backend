package am.ith.service.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getName();

    HttpStatus getStatus();

}
