package pl.michal.olszewski.flashcardsapp.topic;

import java.util.List;
import pl.michal.olszewski.flashcardsapp.cards.Card;
import pl.michal.olszewski.flashcardsapp.cards.CardService;

public class RemoveCardService {

  private final CardService cardService;
  private final TopicService topicService;

  public RemoveCardService(CardService cardService, TopicService topicService) {
    this.topicService = topicService;
    this.cardService = cardService;
  }

  public Topic removeCardsFromTopic(RemoveCardFromTopicDTO dto) {
    Topic topic = topicService.findTopicById(dto.getTopicId());
    List<Card> cardsByIds = cardService.findCardsByIds(dto.getCardsIds());
    cardsByIds.forEach(topic::removeCard);
    return topic;
  }
}
