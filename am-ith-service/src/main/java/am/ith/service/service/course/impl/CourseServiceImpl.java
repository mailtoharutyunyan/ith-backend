package am.ith.service.service.course.impl;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.mapper.CourseMapper;
import am.ith.service.mapper.LevelMapper;
import am.ith.service.mapper.TrainerMapper;
import am.ith.service.model.Course;
import am.ith.service.repository.CourseRepository;
import am.ith.service.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;
  private final LevelMapper levelMapper;
  private final TrainerMapper trainerMapper;

  @Override
  public List<CourseResponse> courseResponseList() {
    return courseRepository.findAll().stream()
        .map(
            course ->
                new CourseResponse(
                    course.getCoursePicture(),
                    course.getCourseName(),
                    course.getFirstCourseDescription(),
                    course.getSecondCourseDescription(),
                    levelMapper.toLevelResponseList(course.getLevels()),
                    trainerMapper.toTrainerResponse(course.getTrainers())))
        .collect(Collectors.toList());
  }

  @Override
  public CourseResponse getCourseByUUID(Long id) {
    return courseMapper.toCourseResponse(courseRepository.getOne(id));
  }

  @Override
  public CourseResponse createCourse(CourseRequest courseRequest) {
    Course course = courseMapper.toCourseModel(courseRequest);
    return courseMapper.toCourseResponse(course);
  }

  @Override
  public CourseRequest updateCourse(String uuid, CourseRequest courseRequest) {
    return null;
  }
}
