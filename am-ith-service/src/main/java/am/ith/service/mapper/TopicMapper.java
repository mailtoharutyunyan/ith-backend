package am.ith.service.mapper;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.model.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TopicMapper {

  public TopicResponse toTopicResponse(Topic topic) {
    return TopicResponse.builder()
        .topicDetails(topic.getTopicDetails())
        .build();
  }

  public Topic toTopic(TopicRequest topicRequest) {
    return Topic.builder().topicDetails(topicRequest.getTopicDetails()).build();
  }

  public List<TopicResponse> toTopicResponseList(List<Topic> topics) {
    return topics.stream()
        .map(topic -> new TopicResponse(topic.getTopicDetails()))
        .collect(Collectors.toList());
  }

}
