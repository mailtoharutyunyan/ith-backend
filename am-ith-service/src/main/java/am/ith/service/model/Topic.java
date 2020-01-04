package am.ith.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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


  private String topicDetails;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "level_id", nullable = false)
  @JsonIgnore
  private Level level;

}
