package am.it.api.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto implements Serializable {

  private static final long serialVersionUID = -814392692255908128L;

  @NotNull(message = "Birthday is required")
  private LocalDateTime birthday;

  @Size(max = 100, message = "Description should not more than 100 characters.")
  private String description;

  @NotEmpty(message = "Last name is required")
  private String lastName;

  @NotEmpty(message = "First name is required")
  private String firstName;

  private String middleName;
}
