package am.ith.service.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
public class Topic extends BaseEntity {

  private UUID uuid;

  private String topicDetails;

  @ManyToOne
  private Level level;

}
