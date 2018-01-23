package pl.michal.olszewski.flashcardsapp.topic.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicFactory;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;


class TopicReadObjectMapperTest {

  private TopicReadObjectMapper mapper = new TopicReadObjectMapper();

  @Test
  void shouldConvertTopicToTopicDTO() {
    //given
    Topic topic = TopicFactory.build(2L, "nazwa");
    //when
    TopicDTO topicDTO = mapper.convertToDTO(topic);
    //then
    assertAll(
        () -> assertThat(topicDTO.getId()).isEqualTo(2L),
        () -> assertThat(topicDTO.getName()).isEqualTo("nazwa")
    );
  }

  @Test
  void shouldConvertTopicWithCardsToTopicDTO() {
    //given
    Topic topic = TopicFactory.build(2L, "nazwa");
    Card cardOne = CardFactory.build(1L);
    Card cardTwo = CardFactory.build(2L);
    topic.addCard(cardOne);
    topic.addCard(cardTwo);
    //when
    TopicDTO topicDTO = mapper.convertToDTO(topic);
    //then
    assertAll(
        () -> assertThat(topicDTO.getId()).isEqualTo(2L),
        () -> assertThat(topicDTO.getName()).isEqualTo("nazwa"),
        () -> assertThat(topicDTO.getCards()).hasSize(2)
    );
  }

}