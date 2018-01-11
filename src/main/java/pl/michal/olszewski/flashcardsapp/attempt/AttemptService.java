package pl.michal.olszewski.flashcardsapp.attempt;

import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.dto.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.dto.NewAttemptDTO;

@Service
public class AttemptService {

  private final AttemptObjectMapper objectMapper;
  private final AttemptRepository attemptRepository;

  public AttemptService(AttemptObjectMapper objectMapper, AttemptRepository attemptRepository) {
    this.objectMapper = objectMapper;
    this.attemptRepository = attemptRepository;
  }

  public Attempt createNewAttempt(NewAttemptDTO newAttemptDTO) {
    Attempt attempt = objectMapper.convertFromDTO(newAttemptDTO);
    attempt.getTest().addAttempt(attempt);
    attemptRepository.save(attempt);
    return attempt;
  }

  public Attempt closeAttempt(CloseAttemptDTO closeAttemptDTO) {
    Attempt attempt = attemptRepository.findOne(closeAttemptDTO.getAttemptId());
    attempt.setAttemptStatus(AttemptStatusEnum.DONE.getValue());
    return attempt;
  }
}
