package am.it.api.course.response;

import am.it.api.trainer.response.TrainerResponse;
import am.it.api.level.response.LevelResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CourseResponse {

  private Long courseId;
  private String coursePicture;
  private String courseName;
  private String courseFirstDescription;
  private String courseSecondDescription;
  private List<LevelResponse> levels;
  private List<TrainerResponse> trainers;
}
