package am.it.api.topic;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TopicApi {

  @PostMapping(value = "/level/{levelId}/topic")
  ResponseEntity<TopicResponse> createLevel(
      @PathVariable String levelId, @RequestBody TopicRequest topicRequest, Errors errors)
      throws Exception;
}
