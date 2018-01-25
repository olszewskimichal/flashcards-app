package pl.michal.olszewski.flashcardsapp.examcards.read.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ExamCard extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "card_id")
  private Card card;

  @OneToOne
  @JoinColumn(name = "attempt_id")
  private Attempt attempt;

  private Long testLevel;

  private Boolean isCorrect;

  @Builder
  public ExamCard(Long id, Card card, Attempt attempt, Long testLevel, Boolean isCorrect) {
    super(id);
    this.card = card;
    this.attempt = attempt;
    this.testLevel = testLevel;
    this.isCorrect = isCorrect;
  }

  //JPA - WHY
  public ExamCard() {
  }
}