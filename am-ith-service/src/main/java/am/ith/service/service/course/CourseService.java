package am.ith.service.service.course;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.model.Course;

import java.util.List;

public interface CourseService {

  List<CourseResponse> courseResponseList();

  CourseResponse getCourseById(Long id);

  CourseResponse createCourse(CourseRequest courseRequest);

  CourseRequest updateCourse(String courseId, CourseRequest courseRequest);
}
