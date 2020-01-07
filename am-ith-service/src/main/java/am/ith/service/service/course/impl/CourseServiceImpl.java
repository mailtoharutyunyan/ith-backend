package am.ith.service.service.course.impl;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.exception.ContentNotFoundException;
import am.ith.service.exception.CourseNotFoundException;
import am.ith.service.mapper.CourseMapper;
import am.ith.service.model.Course;
import am.ith.service.repository.CourseRepository;
import am.ith.service.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  @Override
  public List<CourseResponse> getAllCourses() {

    final List<Course> courses = this.courseRepository.findAll();
    return courses.stream().map(this.courseMapper::toCourseResponse).collect(Collectors.toList());
  }

  @Override
  public CourseResponse getCourseById(final Long id) {
    final CourseResponse courseResponse =
        this.courseMapper.toCourseResponse(this.courseRepository.getOne(id));
    log.info("Get Course By Id after course mapper {}", courseResponse);
    return courseResponse;
  }

  @Override
  public CourseResponse createCourse(final CourseRequest courseRequest) {
    final Course course = this.courseMapper.toCourseModel(courseRequest);
    final Course savedCourse = this.courseRepository.saveAndFlush(course);
    return this.getCourseById(savedCourse.getId());
  }

  @Override
  public CourseResponse updateCourse(final Long id, final CourseRequest courseRequest) {
    final Course findCourse =
            this.courseRepository
            .findById(id)
            .orElseThrow(() -> new ContentNotFoundException("Can't find course"));

    final Course course = this.courseMapper.toCourseModel(courseRequest);
    final Course combinedCourse = this.courseMapper.combineCourses(course, findCourse);
    final Course updatedCourse = this.courseRepository.saveAndFlush(combinedCourse);
    final CourseResponse courseResponse = this.courseMapper.toCourseResponse(updatedCourse);
    log.info("Course response {}", courseResponse);
    return courseResponse;
  }

  @Override
  public CourseResponse deleteCourseById(final Long id) throws CourseNotFoundException {
    final Course course =
            this.courseRepository
            .findById(id)
            .orElseThrow(() -> new CourseNotFoundException("Can't find course"));
    this.courseRepository.deleteById(id);
    final CourseResponse courseResponse = this.courseMapper.toCourseResponse(course);
    log.info("Course response {}", courseResponse);
    return courseResponse;
  }
}
