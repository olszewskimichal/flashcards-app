package pl.michal.olszewski.flashcardsapp.factory.user;

import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

public class UserFactory {

  public static User build(Long id, String firstName, String lastName) {
    return User.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

  public static User build(Long id) {
    return build(id, "", "");
  }

}
