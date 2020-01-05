package am.ith.service.mapper;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.it.api.level.response.LevelResponse;
import am.it.api.topic.response.TopicResponse;
import am.it.api.trainer.response.TrainerResponse;
import am.ith.service.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class CourseMapper {

  public Course toCourseModel(final CourseRequest courseRequest) {
    return Course.builder()
        .courseName(courseRequest.getCourseName())
        .firstCourseDescription(courseRequest.getCourseFirstDescription())
        .secondCourseDescription(courseRequest.getCourseSecondDescription())
        .coursePicture(courseRequest.getCoursePicture())
        .build();
  }

  public Course combineCourses(final Course course, final Course finalCourse) {
    finalCourse.setLevels(course.getLevels());
    finalCourse.setCourseName(course.getCourseName());
    finalCourse.setCoursePicture(course.getCoursePicture());
    finalCourse.setFirstCourseDescription(course.getFirstCourseDescription());
    finalCourse.setSecondCourseDescription(course.getSecondCourseDescription());
    finalCourse.setTrainers(course.getTrainers());
    return finalCourse;
  }

  public CourseResponse toCourseResponse(final Course course) {

    List<TrainerResponse> trainerResponseList = new ArrayList<>();
    List<LevelResponse> levelResponseList = new ArrayList<>();
    if (course.getTrainers() != null) {
      trainerResponseList =
          course.getTrainers().stream()
              .map(
                  trainer ->
                      new TrainerResponse(
                          trainer.getTrainerName(),
                          trainer.getDeveloperType(),
                          trainer.getDeveloperDescription(),
                          trainer.getFacebookLink(),
                          trainer.getLinkedinLink(),
                          trainer.getDeveloperImage()))
              .collect(Collectors.toList());
    }

    if (course.getLevels() != null) {
      levelResponseList =
          course.getLevels().stream()
              .map(
                  level -> {
                    List<TopicResponse> topics =
                        level.getTopics().stream()
                            .map(topic -> new TopicResponse(topic.getId(), topic.getTopicDetails()))
                            .collect(Collectors.toList());

                    return new LevelResponse(level.getId(), level.getLevelNumber(), topics);
                  })
              .collect(Collectors.toList());
    }

    return CourseResponse.builder()
        .courseId(course.getId())
        .courseFirstDescription(course.getFirstCourseDescription())
        .courseSecondDescription(course.getSecondCourseDescription())
        .coursePicture(course.getCoursePicture())
        .courseName(course.getCourseName())
        .levels(levelResponseList)
        .trainers(trainerResponseList)
        .build();
  }
}
