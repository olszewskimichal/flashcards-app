package pl.michal.olszewski.flashcardsapp.exam.write;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class CreateExamDTO implements DataTransferObject {

  private final Long topicId;

}
