package pl.michal.olszewski.flashcardsapp.topic.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicDTOFactory;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;


class TopicWriteObjectMapperTest {

  private TopicWriteObjectMapper mapper = new TopicWriteObjectMapper();

  @Test
  void shouldConvertFromTopicDTO() {
    //given
    TopicDTO topicDTO = TopicDTOFactory.build(1L, "name");
    //when
    Topic topic = mapper.convertFromDTO(topicDTO);
    //then
    assertAll(
        () -> assertThat(topic.getName()).isEqualTo("name")
    );
  }
}