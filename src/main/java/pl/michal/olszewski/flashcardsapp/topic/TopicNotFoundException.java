package pl.michal.olszewski.flashcardsapp.topic;

public class TopicNotFoundException extends RuntimeException {

  public TopicNotFoundException(Long cardId) {
    super(String.format("Nie znalaziono tematu o id = %s", cardId));
  }


}
