package pl.michal.olszewski.flashcardsapp.factory.attempt;

import java.time.Instant;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

public class AttemptFactory {

  public static Attempt buildAttemptWithUserAndExam(Long attemptId, Long userId, Long examId) {
    return Attempt.builder()
        .id(attemptId)
        .user(User.builder().id(userId).build())
        .exam(Exam.builder().id(examId).build())
        .startDateTime(Instant.now())
        .attemptStatus(AttemptStatusEnum.DONE)
        .attemptCount(1L)
        .build();
  }

  public static Attempt build(Long attemptId) {
    return buildAttemptWithUserAndExam(attemptId, null, null);
  }

}
