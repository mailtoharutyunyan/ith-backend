package am.ith.server.topic;

import am.it.api.topic.TopicApi;
import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.server.validator.RequestFieldsValidator;
import am.ith.service.service.topic.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class TopicController implements TopicApi {

  private final TopicService topicService;
  private final RequestFieldsValidator requestFieldsValidator;

  @Override
  public ResponseEntity<TopicResponse> createTopic(
      String levelId, TopicRequest topicRequest, Errors errors) throws Exception {

    requestFieldsValidator.validate(errors);
    TopicResponse topicResponse = topicService.createTopic(Long.parseLong(levelId), topicRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(topicResponse);
  }

  @Override
  public ResponseEntity<TopicResponse> updateTopicById(
      final String topicId, final TopicRequest topicRequest, final Errors errors) throws Exception {

    requestFieldsValidator.validate(errors);
    TopicResponse topicResponse =
        topicService.updateTopicById(Long.parseLong(topicId), topicRequest);
    return ResponseEntity.status(HttpStatus.OK).body(topicResponse);
  }

  @Override
  public ResponseEntity<TopicResponse> deleteTopicById(String topicId) throws Exception {

    TopicResponse topicResponse = topicService.deleteById(Long.parseLong(topicId));
    return ResponseEntity.status(HttpStatus.OK).body(topicResponse);
  }
}
