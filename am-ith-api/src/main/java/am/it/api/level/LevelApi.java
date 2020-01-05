package am.it.api.level;

import am.it.api.level.request.LevelRequest;
import am.it.api.level.response.LevelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LevelApi {
  @PostMapping(value = "/courses/{courseId}/level")
  ResponseEntity<LevelResponse> createLevel(@PathVariable String courseId, @RequestBody LevelRequest levelRequest)
      throws Exception;
}
