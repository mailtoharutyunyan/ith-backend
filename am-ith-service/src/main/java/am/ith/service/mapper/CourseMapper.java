package am.ith.service.mapper;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.it.api.level.response.LevelResponse;
import am.it.api.topic.response.TopicResponse;
import am.it.api.trainer.response.TrainerResponse;
import am.ith.service.model.Course;
import am.ith.service.model.Level;
import am.ith.service.model.Topic;
import am.ith.service.model.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class CourseMapper {

  private final LevelMapper levelMapper;
  private final TrainerMapper trainerMapper;

  public List<CourseResponse> toCourseResponseList(List<Course> courses, List<Topic> topics) {
    return courses.stream()
        .map(
            course ->
                new CourseResponse(
                    course.getId(),
                    course.getCoursePicture(),
                    course.getCourseName(),
                    course.getFirstCourseDescription(),
                    course.getSecondCourseDescription(),
                    levelMapper.toLevelResponseList(course.getLevels(), topics),
                    trainerMapper.toTrainerResponse(course.getTrainers())))
        .collect(Collectors.toList());
  }

  public Course toCourseModel(CourseRequest courseRequest) {
    return Course.builder()
        .courseName(courseRequest.getCourseName())
        .firstCourseDescription(courseRequest.getCourseFirstDescription())
        .secondCourseDescription(courseRequest.getCourseSecondDescription())
        .coursePicture(courseRequest.getCoursePicture())
        .build();
  }

  public CourseResponse toCourseResponse(
      Course course, List<Trainer> trainerList, List<Topic> topicList, List<Level> levelList) {

    List<TrainerResponse> trainerResponseList =
        trainerList.stream()
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

    List<TopicResponse> topicResponses =
        topicList.stream()
            .map(topic -> new TopicResponse(topic.getTopicDetails()))
            .collect(Collectors.toList());
    List<LevelResponse> levelResponseList =
        levelList.stream()
            .map(level -> new LevelResponse(level.getLevelNumber(), topicResponses))
            .collect(Collectors.toList());

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
