package am.it.api.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse implements Serializable {

  private static final long serialVersionUID = -215509000454659840L;

  private String token;
}
