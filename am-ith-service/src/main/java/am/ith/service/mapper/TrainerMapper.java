package am.ith.service.mapper;

import am.it.api.trainer.request.TrainerRequest;
import am.it.api.trainer.response.TrainerResponse;
import am.ith.service.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TrainerMapper {

  public Trainer toTeacher(final TrainerRequest trainerRequest) {
    return Trainer.builder()
        .trainerName(trainerRequest.getTrainerName())
        .developerType(trainerRequest.getDeveloperType())
        .developerDescription(trainerRequest.getDeveloperDescription())
        .facebookLink(trainerRequest.getFacebookLink())
        .linkedinLink(trainerRequest.getLinkedinLink())
        .developerImage(trainerRequest.getTrainerPicture())
        .build();
  }

  public TrainerResponse toTrainerResponse(final Trainer trainer) {
    return TrainerResponse.builder()
        .trainerName(trainer.getTrainerName())
        .trainerPicture(trainer.getDeveloperImage())
        .developerDescription(trainer.getDeveloperDescription())
        .developerType(trainer.getDeveloperType())
        .facebookLink(trainer.getFacebookLink())
        .linkedinLink(trainer.getLinkedinLink())
        .build();
  }

  public List<TrainerResponse> toTrainerResponse(final List<Trainer> trainers) {
    return trainers.stream()
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
}
