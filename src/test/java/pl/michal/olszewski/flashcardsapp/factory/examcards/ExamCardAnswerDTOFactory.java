package pl.michal.olszewski.flashcardsapp.factory.examcards;

import pl.michal.olszewski.flashcardsapp.examcards.write.answer.dto.ExamCardAnswerDTO;

public class ExamCardAnswerDTOFactory {

  public static ExamCardAnswerDTO build(Long id, String answer) {
    return ExamCardAnswerDTO.builder()
        .examCardId(id)
        .answer(answer)
        .build();
  }
}
