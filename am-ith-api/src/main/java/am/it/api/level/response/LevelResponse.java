package am.it.api.level.response;

import am.it.api.topic.TopicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class LevelResponse {

  private UUID uuid;

  private String levelNumber;

  private List<TopicResponse> topics;
}
