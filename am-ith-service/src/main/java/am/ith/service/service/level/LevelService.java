package am.ith.service.service.level;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;

import java.util.List;

public interface LevelService {

  LevelResponse createLevel(Long courseId, LevelRequest levelRequest) throws Exception;

  LevelResponse getLevelById(Long id);

  LevelResponse deleteLevelById(Long id);

  List<LevelResponse> getAllLevels();

  LevelResponse updateLevel(Long id, LevelRequest levelRequest);

}
