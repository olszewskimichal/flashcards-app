package pl.michal.olszewski.flashcardsapp.attempt;

import java.util.Objects;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.read.dto.AttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.test.TestRepository;
import pl.michal.olszewski.flashcardsapp.time.DateTimeService;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;

@Component("AttemptObjectMapper")
public class AttemptObjectMapper {

  private final UserRepository userRepository;
  private final TestRepository testRepository;
  private final DateTimeService timeService;

  public AttemptObjectMapper(UserRepository userRepository, TestRepository testRepository, DateTimeService timeService) {
    this.userRepository = userRepository;
    this.testRepository = testRepository;
    this.timeService = timeService;
  }

  public Attempt convertFromDTO(NewAttemptDTO transferObject) {
    Objects.requireNonNull(transferObject.getUserId(), "Nie podano id uzytkownika");
    Objects.requireNonNull(transferObject.getTestId(), "Nie podano id testu");
    return Attempt.builder()
        .attemptCount(transferObject.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.NEW)
        .startDateTime(timeService.getCurrentDateTime())
        .test(testRepository.findOne(transferObject.getTestId()))
        .user(userRepository.findOne(transferObject.getUserId()))
        .build();
  }

  public AttemptDTO convertToDTO(Attempt entity) {
    return AttemptDTO.builder()
        .attemptId(entity.getId())
        .testId(entity.getTest().getId())
        .userId(entity.getUser().getId())
        .attemptCount(entity.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.fromValue(entity.getAttemptStatus()))
        .startDateTime(entity.getStartDateTime())
        .endDateTime(entity.getEndDateTime())
        .build();
  }

  public Attempt updateFrom(UpdateStatusAttemptDTO transferObject, Attempt entity) {
    entity.setAttemptStatus(transferObject.getAttemptStatus());
    return entity;
  }


}
