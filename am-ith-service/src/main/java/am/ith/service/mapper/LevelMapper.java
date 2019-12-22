package am.ith.service.mapper;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.ith.service.model.Level;
import am.ith.service.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelMapper {

  private final TopicRepository topicRepository;
  private final TopicMapper topicMapper;

  Level toLevel(LevelRequest levelRequest) {
    return Level.builder()
        .levelNumber(levelRequest.getLevelNumber())
        .topics(topicRepository.findAll())
        .build();
  }

  LevelResponse toLevelResponse(Level level) {
    return LevelResponse.builder()
        .levelNumber(level.getLevelNumber())
        .uuid(level.getUuid())
        .topics(topicMapper.toTopicResponseList(level.getTopics()))
        .build();
  }
}
