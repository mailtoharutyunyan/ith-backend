package am.ith.service.mapper;

import am.it.api.course.request.CourseRequest;
import am.it.api.course.response.CourseResponse;
import am.it.api.topic.TopicResponse;
import am.it.api.trainer.response.TrainerResponse;
import am.it.api.level.response.LevelResponse;
import am.ith.service.model.Course;
import am.ith.service.model.Level;
import am.ith.service.model.Topic;
import am.ith.service.model.Trainer;
import am.ith.service.repository.LevelRepository;
import am.ith.service.repository.TopicRepository;
import am.ith.service.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper {

  private final LevelRepository levelRepository;
  private final TrainerRepository trainerRepository;
  private final TopicRepository topicRepository;

  Course toCourseModel(CourseRequest courseRequest) {
    return Course.builder()
        .firstCourseDescription(courseRequest.getCourseFirstDescription())
        .secondCourseDescription(courseRequest.getCourseSecondDescription())
        .coursePicture(courseRequest.getCoursePicture())
        .build();
  }

  CourseResponse toCourseResponse(Course course) {
    List<Trainer> trainerList = trainerRepository.findAll();
    List<TrainerResponse> trainerResponseList =
        trainerList.stream()
            .map(
                trainer ->
                    new TrainerResponse(
                        trainer.getUuid(),
                        trainer.getTrainerName(),
                        trainer.getDeveloperType(),
                        trainer.getDeveloperDescription(),
                        trainer.getFacebookLink(),
                        trainer.getLinkedinLink(),
                        trainer.getDeveloperImage()))
            .collect(Collectors.toList());

    List<Topic> topicList = topicRepository.findAll();
    List<TopicResponse> topicResponses =
        topicList.stream()
            .map(topic -> new TopicResponse(topic.getUuid(), topic.getTopicDetails()))
            .collect(Collectors.toList());

    List<Level> levelList = levelRepository.findAll();
    List<LevelResponse> levelResponseList =
        levelList.stream()
            .map(
                level -> new LevelResponse(level.getUuid(), level.getLevelNumber(), topicResponses))
            .collect(Collectors.toList());

    return CourseResponse.builder()
        .courseFirstDescription(course.getFirstCourseDescription())
        .courseSecondDescription(course.getSecondCourseDescription())
        .coursePicture(course.getCoursePicture())
        .courseName(course.getCourseName())
        .levels(levelResponseList)
        .trainers(trainerResponseList)
        .build();
  }
}
