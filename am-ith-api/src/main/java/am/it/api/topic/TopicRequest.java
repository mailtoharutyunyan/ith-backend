package am.it.api.topic;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TopicRequest {

  private UUID uuid;
  private String topicDetails;
}
