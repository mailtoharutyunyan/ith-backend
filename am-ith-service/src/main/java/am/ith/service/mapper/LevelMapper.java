package am.ith.service.mapper;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.it.api.topic.response.TopicResponse;
import am.ith.service.model.Level;
import am.ith.service.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class LevelMapper {

  private final TopicMapper topicMapper;

  public Level toLevel(final LevelRequest levelRequest, final List<Topic> topicResponses) {
    return Level.builder()
        .levelNumber(levelRequest.getLevelNumber())
        .topics(topicResponses)
        .build();
  }

  public Level combineLevels(final Level level, final Level finalLevel) {
    finalLevel.setLevelNumber(level.getLevelNumber());
    return finalLevel;
  }

  public LevelResponse toLevelResponse(final Level level) {
    return LevelResponse.builder()
        .id(level.getId())
        .levelNumber(level.getLevelNumber())
        .topics(this.topicMapper.toTopicResponseList(level.getTopics()))
        .build();
  }

  public List<LevelResponse> toLevelResponseList(
      final List<Level> levels, final List<Topic> topics) {
    final List<TopicResponse> topicResponses =
        topics.stream()
            .map(topic -> new TopicResponse(topic.getId(), topic.getTopicDetails()))
            .collect(Collectors.toList());
    return levels.stream()
        .map(level -> new LevelResponse(level.getId(), level.getLevelNumber(), topicResponses))
        .collect(Collectors.toList());
  }
}
