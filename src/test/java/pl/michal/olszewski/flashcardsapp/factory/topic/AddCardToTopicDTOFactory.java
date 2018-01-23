package pl.michal.olszewski.flashcardsapp.factory.topic;

import java.util.List;
import pl.michal.olszewski.flashcardsapp.topic.write.addcard.dto.AddCardToTopicDTO;

public class AddCardToTopicDTOFactory {

  public static AddCardToTopicDTO build(Long topicId, List<Long> cardsIds) {
    return AddCardToTopicDTO.builder()
        .topicId(topicId)
        .cardsIds(cardsIds)
        .build();
  }
}
