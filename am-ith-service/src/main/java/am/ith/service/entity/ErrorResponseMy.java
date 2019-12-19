package am.ith.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class ErrorResponseMy implements Serializable {

  private static final long serialVersionUID = -3897564506239586317L;

  private ErrorDto error;

  public ErrorResponseMy(HttpStatus status, String message) {
    this.error = new ErrorDto(status, message);
  }
}
