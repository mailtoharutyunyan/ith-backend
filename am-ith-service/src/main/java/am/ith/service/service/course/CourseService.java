package am.ith.service.service.course;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {

  List<CourseResponse> getAllCourses();

  CourseResponse getCourseById(Long id);

  CourseResponse createCourse(CourseRequest courseRequest);

  CourseResponse updateCourse(Long id, CourseRequest courseRequest);

  CourseResponse deleteCourseById(Long id) throws CourseNotFoundException;
}
