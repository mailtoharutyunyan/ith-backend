package am.it.api.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest implements Serializable {

  private static final long serialVersionUID = 415461304483788372L;

  @NotBlank(message = "Email is required")
  @Email(
      regexp = "(^([^@\\s]){2,32})+(@([^@\\s]){1,15})+\\.([^@\\s]){2,15}",
      message = "Enter your email address in the following format address@example.com")
  private String email;

  @NotBlank(message = "User name is required")
  private String userName;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 16, message = "Password should be 8 - 16 characters long.")
  private String password;
}
