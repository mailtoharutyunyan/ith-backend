package am.it.api.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse implements Serializable {

  private static final long serialVersionUID = -4437270152836645171L;

  private String message;
}
