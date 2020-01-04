package am.it.api.level.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LevelRequest {
  private String levelNumber;
}
