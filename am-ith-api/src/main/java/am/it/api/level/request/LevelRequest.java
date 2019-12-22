package am.it.api.level.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class LevelRequest {
  private UUID uuid;
  private String levelNumber;
}
