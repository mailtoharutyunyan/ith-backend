package am.it.api.topic.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TopicRequest {
  private String topicDetails;
}
