package am.ith.service.mapper;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.model.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TopicMapper {

  public TopicResponse toTopicResponse(final Topic topic) {
    return TopicResponse.builder().id(topic.getId()).topic(topic.getTopicDetails()).build();
  }

  public Topic toTopic(final TopicRequest topicRequest) {
    return Topic.builder().topicDetails(topicRequest.getTopic()).build();
  }

  public Topic combineTopic(final Topic topic, final Topic finalTopic) {
    finalTopic.setTopicDetails(topic.getTopicDetails());
    return finalTopic;
  }

  public List<TopicResponse> toTopicResponseList(final List<Topic> topics) {
    return topics.stream()
        .map(topic -> new TopicResponse(topic.getId(), topic.getTopicDetails()))
        .collect(Collectors.toList());
  }
}
