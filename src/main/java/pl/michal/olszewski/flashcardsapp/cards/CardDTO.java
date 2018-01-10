package pl.michal.olszewski.flashcardsapp.cards;

import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class CardDTO implements DataTransferObject {

  private final Long id;
  private final String question;
  private final String answer;
}
