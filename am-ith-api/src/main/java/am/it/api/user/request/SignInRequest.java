package am.it.api.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest implements Serializable {

  private static final long serialVersionUID = 7119563019099791950L;

  @NotBlank(message = "Field is required")
  private String login;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 16, message = "Password should be 8 - 16 characters long.")
  private String password;
}
