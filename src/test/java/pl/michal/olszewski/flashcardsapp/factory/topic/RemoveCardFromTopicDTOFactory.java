package pl.michal.olszewski.flashcardsapp.factory.topic;

import java.util.List;
import pl.michal.olszewski.flashcardsapp.topic.write.removecard.dto.RemoveCardFromTopicDTO;

public class RemoveCardFromTopicDTOFactory {

  public static RemoveCardFromTopicDTO build(Long topicId, List<Long> cardsIds) {
    return RemoveCardFromTopicDTO.builder()
        .topicId(topicId)
        .cardsIds(cardsIds)
        .build();
  }
}
