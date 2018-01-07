package pl.michal.olszewski.flashcardsapp.topic;

import lombok.Builder;
import lombok.Data;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Data
@Builder
public class TopicDTO implements DataTransferObject {

  private final Long id;
  private String name;
}
