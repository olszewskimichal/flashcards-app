package pl.michal.olszewski.flashcardsapp.factory.user;

import pl.michal.olszewski.flashcardsapp.user.write.dto.create.CreateUserDTO;

public class CreateUserDTOFactory {

  public static CreateUserDTO build(String firstName, String lastName) {
    return CreateUserDTO.builder()
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

}
