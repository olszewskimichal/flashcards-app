package pl.michal.olszewski.flashcardsapp.examcards.question;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
@Builder
public class GetTestCardQuestionDTO implements DataTransferObject {

  private Long attemptId;
}
