package am.it.api.topic;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

public interface TopicApi {

  @PostMapping(value = "/level/{levelId}/topic")
  ResponseEntity<TopicResponse> createTopic(
      @PathVariable final String levelId,
      @RequestBody final TopicRequest topicRequest,
      Errors errors)
      throws Exception;

  @PutMapping(value = "/level/{topicId}")
  ResponseEntity<TopicResponse> updateTopicById(
      @PathVariable final String topicId,
      @RequestBody final TopicRequest topicRequest,
      Errors errors)
      throws Exception;

  @DeleteMapping(value = "/level/{topicId}")
  ResponseEntity<TopicResponse> deleteTopicById(@PathVariable final String topicId)
      throws Exception;
}
