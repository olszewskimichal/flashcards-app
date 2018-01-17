package pl.michal.olszewski.flashcardsapp.topic.write.addcard;

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
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;
import pl.michal.olszewski.flashcardsapp.topic.write.TopicWriteService;
import pl.michal.olszewski.flashcardsapp.topic.write.addcard.dto.AddCardToTopicDTO;

@ExtendWith(MockitoExtension.class)
class AddCardWriteServiceTest {

  private AddCardService addCardService;

  @Mock
  private CardReadService cardReadService;

  @Mock
  private TopicWriteService topicWriteService;

  @BeforeEach
  void setUp() {
    addCardService = new AddCardService(cardReadService, topicWriteService);
  }

  @Test
  void shouldAddCardsToTopic() {
    //given
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    Topic topic = Topic.builder().id(1L).build();
    given(cardReadService.findCardsByIds(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(cardOne, cardTwo));
    given(topicWriteService.findTopicById(1L)).willReturn(topic);
    //when
    topic = addCardService.addCardsToTopic(AddCardToTopicDTO.builder().topicId(1L).cardsIds(Arrays.asList(1L, 2L)).build());
    //then
    assertThat(topic.getCards()).hasSize(2);
  }
}