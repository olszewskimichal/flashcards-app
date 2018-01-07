package pl.michal.olszewski.flashcardsapp.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicDTO;


class TopicObjectMapperTest {

  private TopicObjectMapper mapper = new TopicObjectMapper();

  @Test
  void shouldConvertFromTopicDTO() {
    //given
    TopicDTO topicDTO = TopicDTO.builder().name("name").build();
    //when
    Topic topic = mapper.convertFromDTO(topicDTO);
    //then
    assertAll(
        () -> assertThat(topic.getName()).isEqualTo("name")
    );
  }

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
  void shouldUpdateTopicFromTopicDTO() {
    TopicDTO topicDTO = TopicDTO.builder().id(1L).name("newName").build();
    Topic topic = Topic.builder().id(1L).name("name").build();
    mapper.updateFrom(topicDTO, topic);
    //then
    assertAll(
        () -> assertThat(topicDTO.getId()).isEqualTo(1L),
        () -> assertThat(topicDTO.getName()).isEqualTo("newName")
    );
  }
}