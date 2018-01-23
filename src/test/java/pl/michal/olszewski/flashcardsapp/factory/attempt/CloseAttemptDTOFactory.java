package pl.michal.olszewski.flashcardsapp.factory.attempt;

import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.CloseAttemptDTO;

public class CloseAttemptDTOFactory {

  public static CloseAttemptDTO build(Long attemptId) {
    return CloseAttemptDTO.builder()
        .attemptId(attemptId)
        .build();
  }

}
