package pl.michal.olszewski.flashcardsapp.attempt.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class NewAttemptDTO implements DataTransferObject {

  private final Long userId;
  private final Long attemptCount;
  private final Long testId;

  @Builder
  public NewAttemptDTO(Long userId, Long attemptCount, Long testId) {
    this.userId = userId;
    this.attemptCount = attemptCount;
    this.testId = testId;
  }
}
