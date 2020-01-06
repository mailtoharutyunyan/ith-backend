package am.it.api.level;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface LevelApi {
  @PostMapping(value = "/courses/{courseId}/level")
  ResponseEntity<LevelResponse> createLevel(
      @PathVariable final String courseId,
      @RequestBody final LevelRequest levelRequest,
      Errors errors)
      throws Exception;

  @GetMapping(value = "/courses/level/{levelId}")
  ResponseEntity<LevelResponse> getLevelById(@PathVariable final String levelId) throws Exception;

  @DeleteMapping(value = "/courses/level/{levelId}")
  ResponseEntity<LevelResponse> deleteLevelById(@PathVariable final String levelId)
      throws Exception;

  @PutMapping(value = "/courses/level/{levelId}")
  ResponseEntity<LevelResponse> updateLevelById(
      @PathVariable final String levelId, @RequestBody final LevelRequest levelRequest)
      throws Exception;
}
