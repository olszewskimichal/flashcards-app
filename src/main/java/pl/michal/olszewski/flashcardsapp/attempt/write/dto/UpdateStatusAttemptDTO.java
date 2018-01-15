package pl.michal.olszewski.flashcardsapp.attempt.write.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
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
