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
    this.courseRepository
        .findById(courseId)
        .map(
            findCourse -> {
              level[0] = this.levelMapper.toLevel(levelRequest, this.topicRepository.findAll());
              level[0].setCourse(findCourse);
              this.levelRepository.saveAndFlush(level[0]);
              List<Level> levelList = this.levelRepository.findAll();
              levelList.add(level[0]);
              findCourse.setLevels(levelList);
              return this.courseRepository.saveAndFlush(findCourse);
            })
        .orElseThrow(() -> new CourseNotFoundException("Can't find course"));

    final LevelResponse levelResponse = this.levelMapper.toLevelResponse(level[0]);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public LevelResponse getLevelById(final Long levelId) throws LevelNotFountException {

    final Level level =
        this.levelRepository
            .findById(levelId)
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));
    final LevelResponse levelResponse = this.levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public LevelResponse deleteLevelById(final Long id) throws LevelNotFountException {

    final Level level =
        this.levelRepository
            .findById(id)
            .map(
                existingLevel -> {
                  this.levelRepository.deleteById(id);
                  return existingLevel;
                })
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));
    final LevelResponse levelResponse = this.levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }

  @Override
  public List<LevelResponse> getAllLevels() {
    return null;
  }

  @Override
  public LevelResponse updateLevel(final Long id, final LevelRequest levelRequest)
      throws LevelNotFountException {

    final Level level =
        this.levelRepository
            .findById(id)
            .map(
                existingLevel -> {
                  Level currentLevel =
                      this.levelMapper.toLevel(levelRequest, existingLevel.getTopics());
                  Level combinedLevel = this.levelMapper.combineLevels(currentLevel, existingLevel);
                  return this.levelRepository.save(combinedLevel);
                })
            .orElseThrow(() -> new LevelNotFountException("Can't find level"));

    final LevelResponse levelResponse = this.levelMapper.toLevelResponse(level);
    log.info("Level response {}", levelResponse);
    return levelResponse;
  }
}
