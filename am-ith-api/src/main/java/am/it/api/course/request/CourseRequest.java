package am.it.api.course.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseRequest {
  private String coursePicture;
  private String courseName;
  private String courseFirstDescription;
  private String courseSecondDescription;
}
