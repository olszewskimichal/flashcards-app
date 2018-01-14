package pl.michal.olszewski.flashcardsapp.testcards.question;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
@Builder
public class TestCardQuestionDTO implements DataTransferObject {

  private Long testCardId;
  private Long attemptId;
  private String question;
}
