package pl.michal.olszewski.flashcardsapp.attempt.write;

import java.util.Objects;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.ExamRepository;
import pl.michal.olszewski.flashcardsapp.time.DateTimeService;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;

@Component("AttemptWriteObjectMapper")
public class AttemptWriteObjectMapper implements WriteObjectMapper<Attempt, CreateAttemptDTO> {

  private final UserRepository userRepository;
  private final ExamRepository examRepository;
  private final DateTimeService timeService;

  public AttemptWriteObjectMapper(UserRepository userRepository, ExamRepository examRepository, DateTimeService timeService) {
    this.userRepository = userRepository;
    this.examRepository = examRepository;
    this.timeService = timeService;
  }

  @Override
  public Attempt convertFromDTO(CreateAttemptDTO transferObject) {
    Objects.requireNonNull(transferObject.getUserId(), "Nie podano id uzytkownika");
    Objects.requireNonNull(transferObject.getExamId(), "Nie podano id testu");
    return Attempt.builder()
        .attemptCount(transferObject.getAttemptCount())
        .attemptStatus(AttemptStatusEnum.NEW)
        .startDateTime(timeService.getCurrentDateTime())
        .exam(examRepository.findOne(transferObject.getExamId()))
        .user(userRepository.findOne(transferObject.getUserId()))
        .build();
  }


}
