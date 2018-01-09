package pl.michal.olszewski.flashcardsapp.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class UserDTO implements DataTransferObject {

  private final Long id;
  private final String firstName;
  private final String lastName;
}
