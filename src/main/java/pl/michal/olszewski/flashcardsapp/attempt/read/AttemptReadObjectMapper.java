package pl.michal.olszewski.flashcardsapp.attempt.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.attempt.read.dto.AttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;

@Component("AttemptReadObjectMapper")
public class AttemptReadObjectMapper implements ReadObjectMapper<Attempt, AttemptDTO> {

  @Override
  public AttemptDTO convertToDTO(Attempt entity) {
    return AttemptDTO.builder()
        .attemptId(entity.getId())
        .examId(entity.getExam().getId())
        .userId(entity.getUser().getId())
        .attemptCount(entity.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.fromValue(entity.getAttemptStatus()))
        .startDateTime(entity.getStartDateTime())
        .endDateTime(entity.getEndDateTime())
        .build();
  }
}
