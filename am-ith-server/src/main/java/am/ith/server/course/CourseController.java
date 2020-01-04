package am.ith.server.course;

import am.it.api.course.CourseApi;
import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController implements CourseApi {

  private final CourseService courseService;

  /**
   * @param courseRequest Request Model from User
   * @return @CourseResponse
   */
  @Override
  public ResponseEntity<CourseResponse> createCourse(CourseRequest courseRequest) {
    CourseResponse courseResponse = courseService.createCourse(courseRequest);
    return ResponseEntity.ok(courseResponse);
  }

  @Override
  public ResponseEntity<List<CourseResponse>> getAllCourses() {
    List<CourseResponse> courseResponseList = courseService.courseResponseList();
    return ResponseEntity.ok(courseResponseList);
  }
}
