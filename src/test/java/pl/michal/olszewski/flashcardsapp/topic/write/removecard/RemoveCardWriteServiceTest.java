package pl.michal.olszewski.flashcardsapp.topic.write.removecard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.cards.read.CardReadService;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicFactory;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;
import pl.michal.olszewski.flashcardsapp.topic.write.TopicWriteService;
import pl.michal.olszewski.flashcardsapp.topic.write.removecard.dto.RemoveCardFromTopicDTO;

@ExtendWith(MockitoExtension.class)
class RemoveCardWriteServiceTest {

  private RemoveCardService removeCardService;

  @Mock
  private CardReadService cardReadService;

  @Mock
  private TopicWriteService topicWriteService;

  @BeforeEach
  void setUp() {
    removeCardService = new RemoveCardService(cardReadService, topicWriteService);
  }

  @Test
  void shouldRemoveCardsToTopic() {
    //given
    Card cardOne = CardFactory.build(1L);
    Card cardTwo = CardFactory.build(2L);
    Topic topic = TopicFactory.build(1L, "");
    topic.getCards().add(cardOne);
    topic.getCards().add(cardTwo);
    given(cardReadService.findCardsByIds(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(cardOne, cardTwo));
    given(topicWriteService.findTopicById(1L)).willReturn(topic);
    //when
    topic = removeCardService.removeCardsFromTopic(RemoveCardFromTopicDTO.builder().topicId(1L).cardsIds(Arrays.asList(1L, 2L)).build());
    //then
    assertThat(topic.getCards()).hasSize(0);
  }
}