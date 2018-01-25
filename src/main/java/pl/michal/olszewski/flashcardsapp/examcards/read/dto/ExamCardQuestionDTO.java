package pl.michal.olszewski.flashcardsapp.examcards.read.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
@Builder
public class ExamCardQuestionDTO implements DataTransferObject {

  private Long testCardId;
  private Long attemptId;
  private String question;
}