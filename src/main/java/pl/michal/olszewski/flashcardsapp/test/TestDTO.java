package pl.michal.olszewski.flashcardsapp.test;

import lombok.Builder;
import lombok.Data;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Data
public class TestDTO implements DataTransferObject {

  private final Long topicId;

}
