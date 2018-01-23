package pl.michal.olszewski.flashcardsapp.factory.attempt;

import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;

public class CreateAttemptDTOFactory {

  public static CreateAttemptDTO buildWithUserAndExam(Long userId, Long examId) {
    return CreateAttemptDTO.builder()
        .attemptCount(1L)
        .userId(userId)
        .examId(examId)
        .build();
  }

}
