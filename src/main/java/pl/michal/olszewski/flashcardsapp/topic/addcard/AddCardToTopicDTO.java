package pl.michal.olszewski.flashcardsapp.topic.addcard;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class AddCardToTopicDTO implements DataTransferObject {

  private final Long topicId;
  private final List<Long> cardsIds;
}
