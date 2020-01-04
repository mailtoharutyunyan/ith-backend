package am.ith.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Trainer extends BaseEntity {

  private String trainerName;

  private String developerType;

  private String developerDescription;

  private String facebookLink;

  private String linkedinLink;

  private String developerImage;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  @JsonIgnore
  private Course course;
}
