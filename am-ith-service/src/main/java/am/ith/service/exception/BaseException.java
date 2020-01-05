package am.ith.service.exception;

import am.ith.service.error.ErrorCode;
import am.ith.service.error.ErrorFieldResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
  private ErrorCode errorCode;
  private List<ErrorFieldResponseDto> errors;

  public BaseException(ErrorCode errorCode) {
    this(errorCode, null);
  }

  public BaseException(ErrorCode errorCode, List<ErrorFieldResponseDto> errors) {
    this.errorCode = errorCode;
    this.errors = errors;
  }
}
