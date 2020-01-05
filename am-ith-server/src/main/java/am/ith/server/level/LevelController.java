package am.ith.server.level;

import am.it.api.level.LevelApi;
import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import am.ith.service.service.level.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class LevelController implements LevelApi {

  private final LevelService levelService;

  @Override
  public ResponseEntity<LevelResponse> createLevel(String courseId, LevelRequest levelRequest)
      throws Exception {
    LevelResponse responseLevel = levelService.createLevel(Long.parseLong(courseId), levelRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseLevel);
  }
}
