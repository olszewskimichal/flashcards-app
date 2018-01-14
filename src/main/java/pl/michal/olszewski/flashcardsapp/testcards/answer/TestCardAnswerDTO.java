package pl.michal.olszewski.flashcardsapp.testcards.answer;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class TestCardAnswerDTO implements DataTransferObject {

  private Long testCardId;
  private String answer;
}
