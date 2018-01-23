package pl.michal.olszewski.flashcardsapp.factory.user;

import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;

public class UserDTOFactory {

  public static UserDTO build(Long id, String firstName, String lastName) {
    return UserDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

  public static UserDTO build(Long id) {
    return build(id, "", "");
  }

}
