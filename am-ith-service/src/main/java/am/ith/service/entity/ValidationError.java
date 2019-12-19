package am.ith.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ValidationError implements Serializable {
  private static final long serialVersionUID = 3065931849408432008L;

  private String code;
  private String field;
  private String defaultMessage;
}
