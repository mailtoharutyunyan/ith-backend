package am.ith.server.course;

import am.it.api.course.CourseApi;
import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.server.validator.RequestFieldsValidator;
import am.ith.service.exception.CourseNotFoundException;
import am.ith.service.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseController implements CourseApi {

  private final CourseService courseService;
  private final RequestFieldsValidator requestFieldsValidator;

  @Override
  public ResponseEntity<CourseResponse> createCourse(
      final CourseRequest courseRequest, final Errors errors) {
    requestFieldsValidator.validate(errors);
    CourseResponse courseResponse = courseService.createCourse(courseRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
  }

  @Override
  public ResponseEntity<List<CourseResponse>> getAllCourses() {
    List<CourseResponse> courseResponseList = courseService.getAllCourses();
    return ResponseEntity.status(HttpStatus.OK).body(courseResponseList);
  }

  @Override
  public ResponseEntity<CourseResponse> getCourseById(final String courseId) {
    final CourseResponse courseResponse = courseService.getCourseById(Long.parseLong(courseId));
    return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
  }

  @Override
  public ResponseEntity<CourseResponse> deleteCourseById(final String courseId)
      throws CourseNotFoundException {
    final CourseResponse courseResponse = courseService.deleteCourseById(Long.parseLong(courseId));
    return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
  }

  @Override
  public ResponseEntity<CourseResponse> updateCourse(
      final String courseId, @Valid final CourseRequest courseRequest,Errors errors) {
    requestFieldsValidator.validate(errors);
    final CourseResponse courseResponse =
        courseService.updateCourse(Long.parseLong(courseId), courseRequest);
    return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
  }
}
