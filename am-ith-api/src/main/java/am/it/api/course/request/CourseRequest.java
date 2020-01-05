package am.it.api.course.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
