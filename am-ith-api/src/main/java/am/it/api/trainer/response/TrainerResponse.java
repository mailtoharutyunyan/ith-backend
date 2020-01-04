package am.it.api.trainer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TrainerResponse {
  private String trainerName;
  private String developerType;
  private String developerDescription;
  private String facebookLink;
  private String linkedinLink;
  private String trainerPicture;
}
