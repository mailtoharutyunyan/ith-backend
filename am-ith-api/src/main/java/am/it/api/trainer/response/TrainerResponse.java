package am.it.api.trainer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class TrainerResponse {
  private UUID trainerUUID;
  private String trainerName;
  private String developerType;
  private String developerDescription;
  private String facebookLink;
  private String linkedinLink;
  private String trainerPicture;
}
