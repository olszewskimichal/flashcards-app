package pl.michal.olszewski.flashcardsapp.removecard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.cards.Card;
import pl.michal.olszewski.flashcardsapp.cards.CardService;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicService;

@ExtendWith(MockitoExtension.class)
class RemoveCardServiceTest {

  private RemoveCardService removeCardService;

  @Mock
  private CardService cardService;

  @Mock
  private TopicService topicService;

  @BeforeEach
  void setUp() {
    removeCardService = new RemoveCardService(cardService, topicService);
  }

  @Test
  void shouldRemoveCardsToTopic() {
    //given
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    Topic topic = Topic.builder().id(1L).build();
    topic.getCards().add(cardOne);
    topic.getCards().add(cardTwo);
    given(cardService.findCardsByIds(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(cardOne, cardTwo));
    given(topicService.findTopicById(1L)).willReturn(topic);
    //when
    topic = removeCardService.removeCardsFromTopic(RemoveCardFromTopicDTO.builder().topicId(1L).cardsIds(Arrays.asList(1L, 2L)).build());
    //then
    assertThat(topic.getCards()).hasSize(0);
  }
}