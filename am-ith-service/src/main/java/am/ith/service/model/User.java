package am.ith.service.model;

import am.it.api.dto.user.Role;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractPersistable<Long> implements Serializable {

  private static final long serialVersionUID = 8322041481342023441L;

  @Column(name = "external_id", nullable = false)
  private String externalId;

  @Column(nullable = false, unique = true, length = 65)
  private String email;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Role> roles;

  @Column(name = "user_name", nullable = false, unique = true, length = 45)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  private void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.externalId = UUID.randomUUID().toString();
  }
}
