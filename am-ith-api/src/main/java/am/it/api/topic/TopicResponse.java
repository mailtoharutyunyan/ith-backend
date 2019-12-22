package am.it.api.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class TopicResponse {

  private UUID uuid;
  private String topicDetails;
}
