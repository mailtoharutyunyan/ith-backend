package am.ith.service.mapper;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.it.api.topic.TopicResponse;
import am.ith.service.model.Level;
import am.ith.service.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class LevelMapper {

  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  public Level toLevel(LevelRequest levelRequest) {
    return Level.builder()
        .levelNumber(levelRequest.getLevelNumber())
        .topics(topicRepository.findAll())
        .build();
  }

  public LevelResponse toLevelResponse(Level level) {
    return LevelResponse.builder()
        .levelNumber(level.getLevelNumber())
        .uuid(level.getUuid())
        .topics(topicMapper.toTopicResponseList(level.getTopics()))
        .build();
  }

  public List<LevelResponse> toLevelResponseList(List<Level> levels) {
    List<TopicResponse> topicResponses =
        topicRepository.findAll().stream()
            .map(topic -> new TopicResponse(topic.getUuid(), topic.getTopicDetails()))
            .collect(Collectors.toList());
    return levels.stream()
        .map(level -> new LevelResponse(level.getUuid(), level.getLevelNumber(), topicResponses))
        .collect(Collectors.toList());
  }
}
