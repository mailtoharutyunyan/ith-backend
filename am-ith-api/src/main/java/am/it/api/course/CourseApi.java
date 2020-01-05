package am.it.api.course;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CourseApi {

  @PostMapping(value = "/courses")
  ResponseEntity<CourseResponse> createCourse(
      @Valid @RequestBody final CourseRequest courseRequest, final Errors errors);

  @GetMapping(value = "/courses")
  ResponseEntity<List<CourseResponse>> getAllCourses();

  @GetMapping(value = "/courses/{courseId}")
  ResponseEntity<CourseResponse> getCourseById(@PathVariable final String courseId);

  @DeleteMapping(value = "/courses/{courseId}")
  ResponseEntity<CourseResponse> deleteCourseById(@PathVariable final String courseId)
      throws Exception;

  @PutMapping(value = "/courses/{courseId}")
  ResponseEntity<CourseResponse> updateCourse(
      @PathVariable String courseId, @Valid @RequestBody final CourseRequest courseRequest);
}
