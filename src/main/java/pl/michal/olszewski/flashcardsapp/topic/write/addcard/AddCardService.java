package pl.michal.olszewski.flashcardsapp.topic.write.addcard;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.cards.read.CardReadService;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;
import pl.michal.olszewski.flashcardsapp.topic.write.TopicWriteService;
import pl.michal.olszewski.flashcardsapp.topic.write.addcard.dto.AddCardToTopicDTO;

@Service
public class AddCardService {

  private final CardReadService cardReadService;
  private final TopicWriteService topicWriteService;

  public AddCardService(CardReadService cardReadService, TopicWriteService topicWriteService) {
    this.cardReadService = cardReadService;
    this.topicWriteService = topicWriteService;
  }

  public Topic addCardsToTopic(AddCardToTopicDTO addCardToTopicDTO) {
    Topic topic = topicWriteService.findTopicById(addCardToTopicDTO.getTopicId());
    List<Card> cardsByIds = cardReadService.findCardsByIds(addCardToTopicDTO.getCardsIds());
    cardsByIds.forEach(topic::addCard);
    return topic;
  }

}
