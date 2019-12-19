package am.it.api.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest implements Serializable {

  private static final long serialVersionUID = -7684607687526401050L;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 16, message = "Password should be 8 - 16 characters long.")
  private String newPassword;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 16, message = "Password should be 8 - 16 characters long.")
  private String currentPassword;
}
