package am.ith.service.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image_model")
public class ImageModel extends BaseEntity implements Serializable {

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Lob
  @Column(name = "pic")
  private byte[] pic;
}
