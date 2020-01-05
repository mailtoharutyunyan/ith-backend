package am.it.api.course;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseApi {

  @PostMapping(value = "/courses/create")
  ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest);

  @GetMapping(value = "/courses/getAll")
  ResponseEntity<List<CourseResponse>> getAllCourses();
}
