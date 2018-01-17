package pl.michal.olszewski.flashcardsapp.exam.read.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class ExamDTO implements DataTransferObject {

  private final Long id;
  private final Long topicId;
}
