package pl.michal.olszewski.flashcardsapp.topic.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.topic.read.Topic;
import pl.michal.olszewski.flashcardsapp.topic.read.TopicDTO;


class TopicWriteObjectMapperTest {

  private TopicWriteObjectMapper mapper = new TopicWriteObjectMapper();

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
}