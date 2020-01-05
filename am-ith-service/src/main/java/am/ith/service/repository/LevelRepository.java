package am.ith.service.repository;

import am.it.api.level.response.LevelResponse;
import am.ith.service.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {

  LevelResponse findByCourseId(Long courseId);
}
