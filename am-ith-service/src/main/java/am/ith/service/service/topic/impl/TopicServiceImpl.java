package am.ith.service.service.topic.impl;

import am.it.api.topic.request.TopicRequest;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.exception.TopicNotFoundException;
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
  public TopicResponse createTopic(final Long levelId, final TopicRequest topicRequest)
      throws TopicNotFoundException {

    final Topic[] topic = {null};
      this.levelRepository
        .findById(levelId)
        .map(
            level -> {
              Course course = level.getCourse();
              topic[0] = this.topicMapper.toTopic(topicRequest);
              topic[0].setLevel(level);
              List<Topic> topics = level.getTopics();
              topics.add(topic[0]);
              level.setTopics(topics);
              level.setCourse(course);
              Level savedLevel = this.levelRepository.save(level);
              topic[0] = savedLevel.getTopics().get(savedLevel.getTopics().size() - 1);
              return savedLevel;
            })
        .orElseThrow(TopicNotFoundException::new);

    final TopicResponse topicResponse = this.topicMapper.toTopicResponse(topic[0]);
    log.info("Topic response {}", topicResponse);
    return topicResponse;
  }

  @Override
  public TopicResponse getTopicById(final Long id) {
    return null;
  }

  @Override
  public List<TopicResponse> getTopics() {
    return null;
  }

  @Override
  public TopicResponse deleteById(final Long id) throws TopicNotFoundException {

    final Topic topic =
            this.topicRepository
            .findById(id)
            .map(
                existingTopic -> {
                    this.topicRepository.deleteById(id);
                  return existingTopic;
                })
            .orElseThrow(TopicNotFoundException::new);
    final TopicResponse topicResponse = this.topicMapper.toTopicResponse(topic);
    log.info("Deleted topic {}", topicResponse);
    return topicResponse;
  }

  @Override
  public TopicResponse updateTopicById(final Long id, final TopicRequest topicRequest)
      throws TopicNotFoundException {

    final Topic topic =
            this.topicRepository
            .findById(id)
            .map(
                existingTopic -> {
                  Topic updateTopic = this.topicMapper.toTopic(topicRequest);
                  Topic combinedTopic = this.topicMapper.combineTopic(updateTopic, existingTopic);
                  return this.topicRepository.saveAndFlush(combinedTopic);
                })
            .orElseThrow(() -> new TopicNotFoundException("Can't find topic"));
    final TopicResponse topicResponse = this.topicMapper.toTopicResponse(topic);
    log.info("Topic response {}", topicResponse);
    return topicResponse;
  }
}
