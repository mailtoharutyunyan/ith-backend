package am.ith.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ErrorDto implements Serializable {

  private static final long serialVersionUID = 8692919090233982451L;

  private int code;
  private String type;
  private String message;

  public ErrorDto(HttpStatus status, String message) {
    this.code = status.value();
    this.type = status.name();
    this.message = message;
  }
}
