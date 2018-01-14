package pl.michal.olszewski.flashcardsapp.testcards.question;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;

@Component
public class TestCardQuestionObjectMapper {

  public TestCardQuestionDTO convertToDTO(TestCard entity) {
    return TestCardQuestionDTO.builder().attemptId(entity.getAttempt().getId())
        .question(entity.getCard().getQuestion())
        .testCardId(entity.getId())
        .build();
  }

}
