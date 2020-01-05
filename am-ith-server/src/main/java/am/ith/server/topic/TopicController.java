package am.ith.server.topic;

import am.it.api.topic.TopicApi;
import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.service.topic.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class TopicController implements TopicApi {

  private final TopicService topicService;

  @Override
  public ResponseEntity<TopicResponse> createLevel(String levelId, TopicRequest topicRequest)
      throws Exception {
    TopicResponse topicResponse = topicService.createTopic(Long.parseLong(levelId), topicRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(topicResponse);
  }
}