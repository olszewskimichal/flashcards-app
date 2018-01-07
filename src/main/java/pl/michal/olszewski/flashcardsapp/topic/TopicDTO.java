package pl.michal.olszewski.flashcardsapp.topic;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;
import pl.michal.olszewski.flashcardsapp.cards.CardDTO;

@Data
@Builder
public class TopicDTO implements DataTransferObject {

  private final Long id;
  private final String name;
  private final List<CardDTO> cards;
}
