package pl.michal.olszewski.flashcardsapp.cards.test;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import pl.michal.olszewski.flashcardsapp.attempt.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.cards.Card;

@Entity
@EqualsAndHashCode(callSuper = true)
public class TestCard extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "card_id")
  private Card card;

  @OneToOne
  @JoinColumn(name = "attempt_id")
  private Attempt attempt;

  private Long testLevel;

  private Boolean isCorrect;

  @Builder
  public TestCard(Long id, Card card, Attempt attempt, Long testLevel, Boolean isCorrect) {
    super(id);
    this.card = card;
    this.attempt = attempt;
    this.testLevel = testLevel;
    this.isCorrect = isCorrect;
  }
}
