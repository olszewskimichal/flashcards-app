package pl.michal.olszewski.flashcardsapp.examcards.read.question;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
@Builder
public class GetTestCardQuestionDTO implements DataTransferObject {

  private Long attemptId;
}
