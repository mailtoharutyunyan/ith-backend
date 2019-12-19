package am.ith.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class ValidationErrorResponseMy implements Serializable {

  private static final long serialVersionUID = -4830891954415976756L;

  private int status;
  private List<ValidationError> errors;
}
