package pl.michal.olszewski.flashcardsapp.attempt.write.dto.update;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Getter
public class CloseAttemptDTO implements DataTransferObject {

  private final Long attemptId;


  @Builder
  public CloseAttemptDTO(Long attemptId) {
    this.attemptId = attemptId;

  }
}
