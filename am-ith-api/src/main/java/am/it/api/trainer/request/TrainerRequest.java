package am.it.api.trainer.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainerRequest {
  private String trainerName;
  private String developerType;
  private String developerDescription;
  private String facebookLink;
  private String linkedinLink;
  private String trainerPicture;
}
