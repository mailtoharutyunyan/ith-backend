package am.ith.service.service.course.impl;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.ith.service.mapper.CourseMapper;
import am.ith.service.model.Course;
import am.ith.service.model.Level;
import am.ith.service.model.Trainer;
import am.ith.service.repository.CourseRepository;
import am.ith.service.repository.LevelRepository;
import am.ith.service.repository.TopicRepository;
import am.ith.service.repository.TrainerRepository;
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
    private final TrainerRepository trainerRepository;
    private final TopicRepository topicRepository;
    private final LevelRepository levelRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponse> courseResponseList() {

        List<Course> courses = courseRepository.findAll();
        return courses
                .stream()
                .map(
                        course ->
                                courseMapper.
                                        toCourseResponse(
                                                course,
                                                course.getTrainers(),
                                                course.getLevels()))
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        List<Trainer> trainerList = trainerRepository.findAll();
        List<Level> levelList = levelRepository.findAll();
        CourseResponse courseResponse =
                courseMapper.toCourseResponse(
                        courseRepository.getOne(id), trainerList, levelList);
        log.info("Get Course By Id after course mapper {}", courseResponse);
        return courseResponse;
    }

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = courseMapper.toCourseModel(courseRequest);
        Course savedCourse = courseRepository.saveAndFlush(course);
        return getCourseById(savedCourse.getId());
    }

    @Override
    public CourseRequest updateCourse(String courseId, CourseRequest courseRequest) {
        return null;
    }
}
