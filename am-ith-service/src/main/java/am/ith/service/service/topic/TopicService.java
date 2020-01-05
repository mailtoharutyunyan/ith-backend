package am.ith.service.service.topic;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.exception.TopicNotFoundException;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(Long levelId, TopicRequest topicRequest) throws TopicNotFoundException;

    TopicResponse getTopicById(Long id);

    List<TopicResponse> getTopics();

    TopicResponse deleteById(Long id);

    TopicResponse updateTopic(Long id, TopicRequest topicRequest);
}
