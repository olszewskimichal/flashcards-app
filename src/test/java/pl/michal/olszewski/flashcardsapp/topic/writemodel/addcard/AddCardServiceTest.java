package pl.michal.olszewski.flashcardsapp.topic.writemodel.addcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.CardService;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicService;

@ExtendWith(MockitoExtension.class)
class AddCardServiceTest {

  private AddCardService addCardService;

  @Mock
  private CardService cardService;

  @Mock
  private TopicService topicService;

  @BeforeEach
  void setUp() {
    addCardService = new AddCardService(cardService, topicService);
  }

  @Test
  void shouldAddCardsToTopic() {
    //given
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    Topic topic = Topic.builder().id(1L).build();
    given(cardService.findCardsByIds(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(cardOne, cardTwo));
    given(topicService.findTopicById(1L)).willReturn(topic);
    //when
    topic = addCardService.addCardsToTopic(AddCardToTopicDTO.builder().topicId(1L).cardsIds(Arrays.asList(1L, 2L)).build());
    //then
    assertThat(topic.getCards()).hasSize(2);
  }
}