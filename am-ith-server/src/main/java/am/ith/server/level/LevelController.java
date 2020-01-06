package am.ith.server.level;

import am.it.api.level.LevelApi;
import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.ith.server.validator.RequestFieldsValidator;
import am.ith.service.exception.LevelNotFountException;
import am.ith.service.service.level.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class LevelController implements LevelApi {

  private final LevelService levelService;
  private final RequestFieldsValidator requestFieldsValidator;

  @Override
  public ResponseEntity<LevelResponse> createLevel(
      final String courseId, final LevelRequest levelRequest, final Errors errors)
      throws Exception {

    requestFieldsValidator.validate(errors);
    LevelResponse levelResponse = levelService.createLevel(Long.parseLong(courseId), levelRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(levelResponse);
  }

  @Override
  public ResponseEntity<LevelResponse> getLevelById(final String levelId)
      throws LevelNotFountException {

    LevelResponse levelResponse = levelService.getLevelById(Long.parseLong(levelId));
    return ResponseEntity.status(HttpStatus.OK).body(levelResponse);
  }

  @Override
  public ResponseEntity<LevelResponse> deleteLevelById(final String levelId) throws Exception {

    LevelResponse levelResponse = levelService.deleteLevelById(Long.parseLong(levelId));
    return ResponseEntity.status(HttpStatus.OK).body(levelResponse);
  }

  @Override
  public ResponseEntity<LevelResponse> updateLevelById(
      final String levelId, final LevelRequest levelRequest) throws Exception {

    LevelResponse levelResponse = levelService.updateLevel(Long.parseLong(levelId), levelRequest);
    return ResponseEntity.status(HttpStatus.OK).body(levelResponse);
  }
}
