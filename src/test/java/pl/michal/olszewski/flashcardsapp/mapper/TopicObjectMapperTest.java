package pl.michal.olszewski.flashcardsapp.mapper;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.Card;
import pl.michal.olszewski.flashcardsapp.cards.CardDTO;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicDTO;


class TopicObjectMapperTest {

  private TopicObjectMapper mapper = new TopicObjectMapper();

  @Test
  void shouldConvertFromTopicDTO() {
    //given
    CardDTO cardDTO = CardDTO.builder().answer("answer").question("question").build();
    TopicDTO topicDTO = TopicDTO.builder().cards(singletonList(cardDTO)).build();
    //when
    Topic topic = mapper.convertFromDTO(topicDTO);
    //then
    assertAll(
        () -> assertThat(topic.getCards().size()).isEqualTo(1),
        () -> assertThat(topic.getCards().get(0).getAnswer()).isEqualTo("answer"),
        () -> assertThat(topic.getCards().get(0).getQuestion()).isEqualTo("question")
    );
  }

  @Test
  void shouldConvertTopicToTopicDTO() {
    //given
    Card card = Card.builder().question("question1").answer("answer1").build();
    Topic topic = Topic.builder().cards(singletonList(card)).build();
    //when
    TopicDTO topicDTO = mapper.convertToDTO(topic);
    //then
    assertAll(
        () -> assertThat(topicDTO.getCards().size()).isEqualTo(1),
        () -> assertThat(topicDTO.getCards().get(0).getQuestion()).isEqualTo("question1"),
        () -> assertThat(topicDTO.getCards().get(0).getAnswer()).isEqualTo("answer1")
    );
  }

  @Test
  void shouldUpdateTopicFromTopicDTO() {
    CardDTO cardDTO = CardDTO.builder().answer("answerNew").question("questionNew").id(1L).build();
    CardDTO cardDTO2 = CardDTO.builder().answer("answerNew2").question("questionNew2").build();
    TopicDTO topicDTO = TopicDTO.builder().cards(Arrays.asList(cardDTO, cardDTO2)).build();
    Card card = Card.builder().question("question").answer("answer").id(1L).build();
    Topic topic = Topic.builder().build();
    topic.addCard(card);

    mapper.updateFrom(topicDTO, topic);
    //then
    assertAll(
        () -> assertThat(topic.getCards().size()).isEqualTo(2),
        () -> assertThat(topic.getCards().get(0).getAnswer()).isEqualTo("answerNew"),
        () -> assertThat(topic.getCards().get(0).getQuestion()).isEqualTo("questionNew"),
        () -> assertThat(topic.getCards().get(0).getId()).isEqualTo(1L),
        () -> assertThat(topic.getCards().get(1).getAnswer()).isEqualTo("answerNew2"),
        () -> assertThat(topic.getCards().get(1).getQuestion()).isEqualTo("questionNew2"),
        () -> assertThat(topic.getCards().get(1).getId()).isNull()
    );
  }
}