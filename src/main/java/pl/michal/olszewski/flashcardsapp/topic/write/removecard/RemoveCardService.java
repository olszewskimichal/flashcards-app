package pl.michal.olszewski.flashcardsapp.topic.write.removecard;

import java.util.List;
import pl.michal.olszewski.flashcardsapp.cards.read.CardReadService;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;
import pl.michal.olszewski.flashcardsapp.topic.write.TopicWriteService;
import pl.michal.olszewski.flashcardsapp.topic.write.removecard.dto.RemoveCardFromTopicDTO;

public class RemoveCardService {

  private final CardReadService cardReadService;
  private final TopicWriteService topicWriteService;

  public RemoveCardService(CardReadService cardReadService, TopicWriteService topicWriteService) {
    this.topicWriteService = topicWriteService;
    this.cardReadService = cardReadService;
  }

  public Topic removeCardsFromTopic(RemoveCardFromTopicDTO dto) {
    Topic topic = topicWriteService.findTopicById(dto.getTopicId());
    List<Card> cardsByIds = cardReadService.findCardsByIds(dto.getCardsIds());
    cardsByIds.forEach(topic::removeCard);
    return topic;
  }
}
