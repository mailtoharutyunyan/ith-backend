package am.ith.service.mapper;

import am.it.api.trainer.request.TrainerRequest;
import am.it.api.trainer.response.TrainerResponse;
import am.ith.service.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public final class TeacherMapper {

  Trainer toTeacher(TrainerRequest trainerRequest) {
    return Trainer.builder()
        .trainerName(trainerRequest.getTrainerName())
        .developerType(trainerRequest.getDeveloperType())
        .developerDescription(trainerRequest.getDeveloperDescription())
        .facebookLink(trainerRequest.getFacebookLink())
        .linkedinLink(trainerRequest.getLinkedinLink())
        .developerImage(trainerRequest.getTrainerPicture())
        .build();
  }

  TrainerResponse toTrainerResponse(Trainer trainer) {
    return TrainerResponse.builder()
        .trainerUUID(trainer.getUuid())
        .trainerName(trainer.getTrainerName())
        .trainerPicture(trainer.getDeveloperImage())
        .developerDescription(trainer.getDeveloperDescription())
        .developerType(trainer.getDeveloperType())
        .facebookLink(trainer.getFacebookLink())
        .linkedinLink(trainer.getLinkedinLink())
        .build();
  }
}
