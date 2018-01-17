package pl.michal.olszewski.flashcardsapp.examcards.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;

@Component("ExamCardQuestionObjectMapper")
public class ExamCardQuestionObjectMapper {

  public ExamCardQuestionDTO convertToDTO(ExamCard entity) {
    return ExamCardQuestionDTO.builder().attemptId(entity.getAttempt().getId())
        .question(entity.getCard().getQuestion())
        .testCardId(entity.getId())
        .build();
  }

}
