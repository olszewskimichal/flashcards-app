package pl.michal.olszewski.flashcardsapp.cards;

public class CardNotFoundException extends RuntimeException {

  public CardNotFoundException(Long cardId) {
    super(String.format("Nie znalaziono fiszki o id = %s", cardId));
  }


}
