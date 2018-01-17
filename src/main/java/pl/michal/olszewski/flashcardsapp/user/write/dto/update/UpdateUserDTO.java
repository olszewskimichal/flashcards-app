package pl.michal.olszewski.flashcardsapp.user.write.dto.update;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class UpdateUserDTO implements DataTransferObject {

  private final Long id;
  private final String firstName;
  private final String lastName;
}
