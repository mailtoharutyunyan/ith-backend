package am.ith.service.service.level.impl;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.ith.service.exception.CourseNotFoundException;
import am.ith.service.exception.LevelNotFountException;
import am.ith.service.mapper.LevelMapper;
import am.ith.service.model.Level;
import am.ith.service.repository.CourseRepository;
import am.ith.service.repository.LevelRepository;
import am.ith.service.repository.TopicRepository;
import am.ith.service.service.level.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class LevelServiceImpl implements LevelService {

  private final LevelRepository levelRepository;
  private final CourseRepository courseRepository;
  private final TopicRepository topicRepository;
  private final LevelMapper levelMapper;

  @Override
  public LevelResponse createLevel(final Long courseId, final LevelRequest levelRequest)
      throws CourseNotFoundException {

    final Level[] level = {null};
    courseRepository
        .findById(courseId)
        .map(
            findCourse -> {
              level[0] = levelMapper.toLevel(levelRequest, topicRepository.findAll());
              level[0].setCourse(findCourse);
              levelRepository.saveAndFlush(level[0]);
              List<Level> levelList = levelRepository.findAll();
              levelList.add(level[0]);
              findCourse.setLevels(levelList);
              return courseRepository.saveAndFlush(findCourse);
            })
        .orElseThrow(() -> new CourseNotFoundException("Can't find course"));

    LevelResponse levelResponse = levelMapper.toLevelResponse(level[0]);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public LevelResponse getLevelById(final Long levelId) throws LevelNotFountException {

    final Level level =
        levelRepository
            .findById(levelId)
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));
    final LevelResponse levelResponse = levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public LevelResponse deleteLevelById(Long id) throws LevelNotFountException {

    final Level level =
        levelRepository
            .findById(id)
            .map(
                existingLevel -> {
                  levelRepository.deleteById(id);
                  return existingLevel;
                })
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));
    LevelResponse levelResponse = levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public List<LevelResponse> getAllLevels() {
    return null;
  }

  @Override
  public LevelResponse updateLevel(Long id, LevelRequest levelRequest)
      throws LevelNotFountException {

    final Level level =
        levelRepository
            .findById(id)
            .map(
                existingLevel -> {
                  Level currentLevel = levelMapper.toLevel(levelRequest, existingLevel.getTopics());
                  Level combinedLevel = levelMapper.combineLevels(currentLevel, existingLevel);
                  return levelRepository.saveAndFlush(combinedLevel);
                })
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));

    LevelResponse levelResponse = levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }
}
