package am.it.api.topic.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class TopicResponse {
  private String topicDetails;
}
