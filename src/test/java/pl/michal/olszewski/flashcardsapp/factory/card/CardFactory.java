package pl.michal.olszewski.flashcardsapp.factory.card;

import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

public class CardFactory {

  public static Card build(Long id, String question, String answer) {
    return Card.builder()
        .id(id)
        .question(question)
        .answer(answer)
        .build();
  }

  public static Card build(Long id) {
    return build(id, null, null);
  }
}
