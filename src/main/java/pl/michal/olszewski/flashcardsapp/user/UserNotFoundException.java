package pl.michal.olszewski.flashcardsapp.user;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long userId) {
    super(String.format("Nie znalaziono uzytkownika o id = %s", userId));
  }


}
