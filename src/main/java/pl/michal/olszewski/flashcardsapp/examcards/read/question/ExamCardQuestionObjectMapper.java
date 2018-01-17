package pl.michal.olszewski.flashcardsapp.examcards.read.question;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCard;

@Component
public class ExamCardQuestionObjectMapper {

  public ExamCardQuestionDTO convertToDTO(ExamCard entity) {
    return ExamCardQuestionDTO.builder().attemptId(entity.getAttempt().getId())
        .question(entity.getCard().getQuestion())
        .testCardId(entity.getId())
        .build();
  }

}
