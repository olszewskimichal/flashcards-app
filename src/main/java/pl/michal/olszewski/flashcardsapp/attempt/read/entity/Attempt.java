package pl.michal.olszewski.flashcardsapp.attempt.read.entity;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;


@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Attempt extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  private Instant startDateTime;
  private Instant endDateTime;
  private Long attemptCount;
  private Long attemptStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id")
  private Exam exam;

  @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ExamCard> examCards;

  @Builder
  public Attempt(Long id, User user, Instant startDateTime, Instant endDateTime, Long attemptCount, AttemptStatusEnum attemptStatus, Exam exam) {
    super(id);
    this.user = user;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.attemptCount = attemptCount;
    this.attemptStatus = attemptStatus == null ? null : attemptStatus.getValue();
    this.exam = exam;
  }

  public Attempt() {
  }
}