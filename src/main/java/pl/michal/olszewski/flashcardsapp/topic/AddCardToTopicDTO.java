package pl.michal.olszewski.flashcardsapp.topic;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCardToTopicDTO {

  private final Long topicId;
  private final List<Long> cardsIds;
}
