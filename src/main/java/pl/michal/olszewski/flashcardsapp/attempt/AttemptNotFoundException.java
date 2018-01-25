package pl.michal.olszewski.flashcardsapp.attempt;

public class AttemptNotFoundException extends RuntimeException {

  public AttemptNotFoundException(Long attemptId) {
    super(String.format("Nie znalaziono podejscia do testu o id = %s", attemptId));
  }


}
