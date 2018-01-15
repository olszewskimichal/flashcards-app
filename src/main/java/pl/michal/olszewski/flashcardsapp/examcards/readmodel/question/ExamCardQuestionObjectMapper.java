package pl.michal.olszewski.flashcardsapp.examcards.readmodel.question;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.question.ExamCardQuestionDTO;

@Component
public class ExamCardQuestionObjectMapper {

  public ExamCardQuestionDTO convertToDTO(ExamCard entity) {
    return ExamCardQuestionDTO.builder().attemptId(entity.getAttempt().getId())
        .question(entity.getCard().getQuestion())
        .testCardId(entity.getId())
        .build();
  }

}
