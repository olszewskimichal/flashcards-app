package pl.michal.olszewski.flashcardsapp.factory.user;

import pl.michal.olszewski.flashcardsapp.user.write.dto.update.UpdateUserDTO;

public class UpdateUserDTOFactory {

  public static UpdateUserDTO build(Long id, String firstName, String lastName) {
    return UpdateUserDTO.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

  public static UpdateUserDTO build(Long id) {
    return build(id, "", "");
  }

}
