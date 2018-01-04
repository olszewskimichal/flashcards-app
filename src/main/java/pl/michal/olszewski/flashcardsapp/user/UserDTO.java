package pl.michal.olszewski.flashcardsapp.user;

import lombok.Builder;
import lombok.Data;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Data
@Builder
public class UserDTO implements DataTransferObject {

  private final String firstName;
  private final String lastName;
}
