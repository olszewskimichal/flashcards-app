package pl.michal.olszewski.flashcardsapp.factory.attempt;

import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.UpdateStatusAttemptDTO;

public class UpdateStatusAttemptDTOFactory {

  public static UpdateStatusAttemptDTO build(Long attemptId, AttemptStatusEnum statusEnum) {
    return UpdateStatusAttemptDTO.builder()
        .attemptStatus(statusEnum)
        .attemptId(attemptId)
        .build();
  }

}
