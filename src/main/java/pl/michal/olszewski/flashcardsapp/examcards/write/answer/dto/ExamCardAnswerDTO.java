package pl.michal.olszewski.flashcardsapp.examcards.write.answer.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class ExamCardAnswerDTO implements DataTransferObject {

  private Long examCardId;
  private String answer;
}
