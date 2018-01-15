package pl.michal.olszewski.flashcardsapp.topic.addcard;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.CardService;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicService;

@Service
public class AddCardService {

  private final CardService cardService;
  private final TopicService topicService;

  public AddCardService(CardService cardService, TopicService topicService) {
    this.cardService = cardService;
    this.topicService = topicService;
  }

  public Topic addCardsToTopic(AddCardToTopicDTO addCardToTopicDTO) {
    Topic topic = topicService.findTopicById(addCardToTopicDTO.getTopicId());
    List<Card> cardsByIds = cardService.findCardsByIds(addCardToTopicDTO.getCardsIds());
    cardsByIds.forEach(topic::addCard);
    return topic;
  }

}
