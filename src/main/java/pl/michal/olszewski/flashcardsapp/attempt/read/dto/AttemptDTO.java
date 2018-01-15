package pl.michal.olszewski.flashcardsapp.attempt.read.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class AttemptDTO implements DataTransferObject {

  private final Long attemptId;
  private final Long userId;
  private final Instant startDateTime;
  private final Instant endDateTime;
  private final Long attemptCount;
  private final Long attemptStatus;
  private final Long testId;

  @Builder
  public AttemptDTO(Long attemptId, Long userId, Instant startDateTime, Instant endDateTime, Long attemptCount, AttemptStatusEnum attemptStatus, Long testId) {
    this.attemptId = attemptId;
    this.userId = userId;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.attemptCount = attemptCount;
    this.attemptStatus = attemptStatus.getValue();
    this.testId = testId;
  }
}
