package am.it.api.level.response;

import am.it.api.topic.response.TopicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class LevelResponse {
  private Long id;
  private String levelNumber;
  private List<TopicResponse> topics;
}
