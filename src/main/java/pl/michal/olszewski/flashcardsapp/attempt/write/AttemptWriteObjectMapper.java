package pl.michal.olszewski.flashcardsapp.attempt.write;

import java.util.Objects;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.read.ExamFinder;
import pl.michal.olszewski.flashcardsapp.time.DateTimeService;
import pl.michal.olszewski.flashcardsapp.user.read.UserFinder;

@Component("AttemptWriteObjectMapper")
public class AttemptWriteObjectMapper implements WriteObjectMapper<Attempt, CreateAttemptDTO> {

  private final UserFinder userFinder;
  private final ExamFinder examFinder;
  private final DateTimeService timeService;

  public AttemptWriteObjectMapper(UserFinder userFinder, ExamFinder examFinder, DateTimeService timeService) {
    this.userFinder = userFinder;
    this.examFinder = examFinder;
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
        .exam(examFinder.findById(transferObject.getExamId()).orElse(null))
        .user(userFinder.findById(transferObject.getUserId()).orElse(null))
        .build();
  }


}
