package pl.michal.olszewski.flashcardsapp.testcards.question;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestCardQuestionDTO {
  private Long attemptId;
  private String question;
}
