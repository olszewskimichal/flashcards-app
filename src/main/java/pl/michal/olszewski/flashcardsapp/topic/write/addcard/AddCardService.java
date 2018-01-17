package pl.michal.olszewski.flashcardsapp.topic.write.addcard;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.cards.CardService;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.topic.TopicService;
import pl.michal.olszewski.flashcardsapp.topic.read.Topic;

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
