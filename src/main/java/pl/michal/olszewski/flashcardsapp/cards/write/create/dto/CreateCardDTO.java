package pl.michal.olszewski.flashcardsapp.cards.write.create.dto;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class CreateCardDTO implements DataTransferObject {

  private final String question;
  private final String answer;
}
