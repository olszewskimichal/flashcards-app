package pl.michal.olszewski.flashcardsapp.user.write.dto.create;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class CreateUserDTO implements DataTransferObject {

  private final String firstName;
  private final String lastName;
}
