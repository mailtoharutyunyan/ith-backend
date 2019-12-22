package am.ith.service.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Trainer extends BaseEntity {

  private UUID uuid;

  private String trainerName;

  private String developerType;

  private String developerDescription;

  private String facebookLink;

  private String linkedinLink;

  private String developerImage;

  @ManyToOne
  private Course course;
}
