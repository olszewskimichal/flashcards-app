package pl.michal.olszewski.flashcardsapp.attempt.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class UpdateStatusAttemptDTO implements DataTransferObject {

  private final Long attemptId;
  private final Long attemptStatus;

  @Builder
  public UpdateStatusAttemptDTO(Long attemptId, AttemptStatusEnum attemptStatus) {
    this.attemptId = attemptId;
    this.attemptStatus = attemptStatus.getValue();
  }
}
