package pl.michal.olszewski.flashcardsapp.cards;

import lombok.Builder;
import lombok.Data;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Data
public class CardDTO implements DataTransferObject {
  private final Long id;
  private final String question;
  private final String answer;
}
