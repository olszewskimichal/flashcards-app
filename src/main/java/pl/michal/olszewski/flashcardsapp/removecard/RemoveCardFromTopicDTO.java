package pl.michal.olszewski.flashcardsapp.removecard;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

@Builder
@Getter
public class RemoveCardFromTopicDTO implements DataTransferObject {

  private final Long topicId;
  private final List<Long> cardsIds;
}
