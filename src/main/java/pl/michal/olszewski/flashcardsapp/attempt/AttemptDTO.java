package pl.michal.olszewski.flashcardsapp.attempt;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class AttemptDTO implements DataTransferObject {

  private final Long id;
  private final Long userId;
  private final Instant startDateTime;
  private final Instant endDateTime;
  private final Long attemptCount;
  private final Long attemptStatus;
  private final Long testId;

  @Builder
  public AttemptDTO(Long id, Long userId, Instant startDateTime, Instant endDateTime, Long attemptCount, AttemptStatusEnum attemptStatus, Long testId) {
    this.id = id;
    this.userId = userId;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.attemptCount = attemptCount;
    this.attemptStatus = attemptStatus.getValue();
    this.testId = testId;
  }
}
