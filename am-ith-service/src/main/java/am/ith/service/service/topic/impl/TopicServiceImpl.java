package am.ith.service.service.topic.impl;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.exception.CourseNotFoundException;
import am.ith.service.exception.TopicNotFoundException;
import am.ith.service.mapper.LevelMapper;
import am.ith.service.mapper.TopicMapper;
import am.ith.service.model.Course;
import am.ith.service.model.Level;
import am.ith.service.model.Topic;
import am.ith.service.repository.CourseRepository;
import am.ith.service.repository.LevelRepository;
import am.ith.service.repository.TopicRepository;
import am.ith.service.service.topic.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

  private final LevelRepository levelRepository;
  private final CourseRepository courseRepository;
  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  @Override
  public TopicResponse createTopic(Long levelId, TopicRequest topicRequest)
      throws TopicNotFoundException {

    final Topic[] topic = {null};
    levelRepository
        .findById(levelId)
        .map(
            level -> {
              Course course = level.getCourse();
              topic[0] = topicMapper.toTopic(topicRequest);
              topic[0].setLevel(level);
              List<Topic> topics = level.getTopics();
              topics.add(topic[0]);
              level.setTopics(topics);
              level.setCourse(course);
              return levelRepository.save(level);
            })
        .orElseThrow(TopicNotFoundException::new);

    TopicResponse topicResponse = topicMapper.toTopicResponse(topic[0]);
    log.info("Topic response {}", topicResponse);
    return topicResponse;
  }

  @Override
  public TopicResponse getTopicById(Long id) {
    return null;
  }

  @Override
  public List<TopicResponse> getTopics() {
    return null;
  }

  @Override
  public TopicResponse deleteById(Long id) {
    return null;
  }

  @Override
  public TopicResponse updateTopic(Long id, TopicRequest topicRequest) {
    return null;
  }
}
