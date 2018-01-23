package pl.michal.olszewski.flashcardsapp.factory.examcards;

import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;

public class ExamCardFactory {

  public static ExamCard build(Long id, Attempt attempt, Card card, Long testLevel, Boolean isCorrect) {
    return ExamCard.builder()
        .id(id)
        .attempt(attempt)
        .card(card)
        .testLevel(testLevel)
        .isCorrect(isCorrect)
        .build();
  }

  public static ExamCard build(Long id, Attempt attempt, Card card, Long testLevel) {
    return build(id, attempt, card, testLevel, null);
  }

}
