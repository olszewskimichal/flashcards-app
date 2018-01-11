package pl.michal.olszewski.flashcardsapp.test;

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
import pl.michal.olszewski.flashcardsapp.attempt.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.topic.Topic;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
public class Test extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Topic topic;
  @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Attempt> attempts;

  @Builder
  public Test(Long id, Topic topic, List<Attempt> attempts) {
    super(id);
    this.topic = topic;
    this.attempts = attempts;
  }

  public void addAttempt(Attempt attempt) {
    getAttempts().add(attempt);
    attempt.setTest(this);
  }

  public List<Attempt> getAttempts() {
    if (attempts == null) {
      attempts = new ArrayList<>();
    }
    return attempts;
  }
}
