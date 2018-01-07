package pl.michal.olszewski.flashcardsapp.topic;

public class TopicNotFoundException extends RuntimeException {

  public TopicNotFoundException(Long topicId) {
    super(String.format("Nie znalaziono tematu o id = %s", topicId));
  }


}
