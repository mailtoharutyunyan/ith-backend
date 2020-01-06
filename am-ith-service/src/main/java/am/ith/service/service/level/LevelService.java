package am.ith.service.service.level;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.ith.service.exception.LevelNotFountException;

import java.util.List;

public interface LevelService {

  LevelResponse createLevel(Long courseId, LevelRequest levelRequest) throws Exception;

  LevelResponse getLevelById(Long levelId) throws LevelNotFountException;

  LevelResponse deleteLevelById(Long id) throws LevelNotFountException;

  List<LevelResponse> getAllLevels();

  LevelResponse updateLevel(Long id, LevelRequest levelRequest) throws LevelNotFountException;

}
