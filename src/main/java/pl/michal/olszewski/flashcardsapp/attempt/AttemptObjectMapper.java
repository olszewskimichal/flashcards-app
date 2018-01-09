package pl.michal.olszewski.flashcardsapp.attempt;

import java.util.Objects;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.mapper.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.test.TestRepository;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;

@Component("AttemptObjectMapper")
public class AttemptObjectMapper implements ObjectMapper<Attempt, AttemptDTO> {

  private final UserRepository userRepository;
  private final TestRepository testRepository;

  public AttemptObjectMapper(UserRepository userRepository, TestRepository testRepository) {
    this.userRepository = userRepository;
    this.testRepository = testRepository;
  }

  @Override
  public Attempt convertFromDTO(AttemptDTO transferObject) {
    Objects.requireNonNull(transferObject.getUserId(), "Nie podano id uzytkownika");
    Objects.requireNonNull(transferObject.getTestId(), "Nie podano id testu");
    return Attempt.builder()
        .attemptCount(transferObject.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.fromValue(transferObject.getAttemptStatus()))
        .startDateTime(transferObject.getStartDateTime())
        .endDateTime(transferObject.getEndDateTime())
        .test(testRepository.findOne(transferObject.getTestId()))
        .user(userRepository.findOne(transferObject.getUserId()))
        .build();
  }

  @Override
  public AttemptDTO convertToDTO(Attempt entity) {
    return AttemptDTO.builder()
        .id(entity.getId())
        .testId(entity.getTest().getId())
        .userId(entity.getUser().getId())
        .attemptCount(entity.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.fromValue(entity.getAttemptStatus()))
        .startDateTime(entity.getStartDateTime())
        .endDateTime(entity.getEndDateTime())
        .build();
  }

  @Override
  public Attempt updateFrom(AttemptDTO transferObject, Attempt entity) {
    entity.setAttemptCount(transferObject.getAttemptCount());
    entity.setAttemptStatus(transferObject.getAttemptStatus());
    entity.setEndDateTime(transferObject.getEndDateTime());
    entity.setStartDateTime(transferObject.getStartDateTime());
    return entity;
  }
}
