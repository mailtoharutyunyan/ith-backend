package am.it.api.course;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface CourseApi {

  @PostMapping(value = "/create")
  ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest);

  @GetMapping(value = "/getAll")
  ResponseEntity<List<CourseResponse>> getAllCourses();
}
