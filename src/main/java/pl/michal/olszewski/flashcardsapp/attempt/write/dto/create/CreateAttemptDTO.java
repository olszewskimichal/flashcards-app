package pl.michal.olszewski.flashcardsapp.attempt.write.dto.create;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class CreateAttemptDTO implements DataTransferObject {

  private final Long userId;
  private final Long attemptCount;
  private final Long examId;

  @Builder
  public CreateAttemptDTO(Long userId, Long attemptCount, Long examId) {
    this.userId = userId;
    this.attemptCount = attemptCount;
    this.examId = examId;
  }
}
