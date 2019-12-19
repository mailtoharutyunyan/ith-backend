package am.ith.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
  private String name;
  private String uri;
  private String type;
  private long size;
}
