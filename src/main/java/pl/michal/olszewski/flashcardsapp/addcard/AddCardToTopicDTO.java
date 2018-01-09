package pl.michal.olszewski.flashcardsapp.addcard;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class AddCardToTopicDTO implements DataTransferObject {

  private final Long topicId;
  private final List<Long> cardsIds;
}
