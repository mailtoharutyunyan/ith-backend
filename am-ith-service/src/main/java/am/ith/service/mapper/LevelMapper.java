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

  public Level toLevel(LevelRequest levelRequest, List<Topic> topicResponses) {
    return Level.builder()
        .levelNumber(levelRequest.getLevelNumber())
        .topics(topicResponses)
        .build();
  }

  public LevelResponse toLevelResponse(Level level) {
    return LevelResponse.builder()
        .levelNumber(level.getLevelNumber())
        .topics(topicMapper.toTopicResponseList(level.getTopics()))
        .build();
  }

  public List<LevelResponse> toLevelResponseList(List<Level> levels, List<Topic> topics) {
    List<TopicResponse> topicResponses =
        topics.stream()
            .map(topic -> new TopicResponse(topic.getTopicDetails()))
            .collect(Collectors.toList());
    return levels.stream()
        .map(level -> new LevelResponse(level.getLevelNumber(), topicResponses))
        .collect(Collectors.toList());
  }
}
