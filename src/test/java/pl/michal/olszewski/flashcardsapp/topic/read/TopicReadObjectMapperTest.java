package pl.michal.olszewski.flashcardsapp.topic.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;


class TopicReadObjectMapperTest {

  private TopicReadObjectMapper mapper = new TopicReadObjectMapper();

  @Test
  void shouldConvertTopicToTopicDTO() {
    //given
    Topic topic = Topic.builder().name("nazwa").id(2L).build();
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
    Topic topic = Topic.builder().name("nazwa").id(2L).build();
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
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