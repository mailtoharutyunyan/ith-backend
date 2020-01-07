package am.it.api.course.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
  @NotNull @NotBlank private String coursePicture;
  @NotNull @NotBlank private String courseName;
  @NotNull @NotBlank private String courseFirstDescription;
  @NotNull @NotBlank private String courseSecondDescription;
}
