package pl.michal.olszewski.flashcardsapp.exam.readmodel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.topic.Topic;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
public class Exam extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Topic topic;
  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Attempt> attempts;

  @Builder
  public Exam(Long id, Topic topic, List<Attempt> attempts) {
    super(id);
    this.topic = topic;
    this.attempts = attempts;
  }

  public void addAttempt(Attempt attempt) {
    getAttempts().add(attempt);
    attempt.setExam(this);
  }

  public List<Attempt> getAttempts() {
    if (attempts == null) {
      attempts = new ArrayList<>();
    }
    return attempts;
  }
}
