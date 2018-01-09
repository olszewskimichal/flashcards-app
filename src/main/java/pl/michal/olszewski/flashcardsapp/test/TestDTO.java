package pl.michal.olszewski.flashcardsapp.test;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class TestDTO implements DataTransferObject {

  private final Long topicId;

}
