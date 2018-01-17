package pl.michal.olszewski.flashcardsapp.cards.write.update;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class CardUpdateDTO implements DataTransferObject {

  private final Long id;
  private final String question;
  private final String answer;
}
