package pl.michal.olszewski.flashcardsapp.topic.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicFactory;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@ExtendWith(MockitoExtension.class)
class TopicWriteServiceTest {

  private TopicWriteService topicWriteService;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private WriteObjectMapper<Topic, TopicDTO> writeObjectMapper;

  @BeforeEach
  void setUp() {
    topicWriteService = new TopicWriteService(topicRepository, writeObjectMapper);
  }

  @Test
  void shouldCreateNewTopic() {
    TopicDTO topicDTO = TopicDTO.builder().build();
    given(writeObjectMapper.convertFromDTO(topicDTO)).willReturn(Topic.builder().build());
    Topic topic = topicWriteService.createTopic(topicDTO);

    assertThat(topic).isNotNull();
    verify(topicRepository, times(1)).save(topic);
  }

  @Test
  void shouldUpdateTopicFromTopicDTO() {
    TopicDTO topicDTO = TopicDTOFactory.build(1L, "newName");
    Topic topic = TopicFactory.build(1L, "name");
    given(topicRepository.findById(1L)).willReturn(Optional.of(topic));
    topicWriteService.updateTopic(topicDTO);
    //then
    assertAll(
        () -> assertThat(topicDTO.getId()).isEqualTo(1L),
        () -> assertThat(topicDTO.getName()).isEqualTo("newName")
    );
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingTopic() {
    TopicDTO topicDTO = TopicDTOFactory.build(1L, "");
    given(topicRepository.findById(1L)).willReturn(Optional.empty());

    TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> topicWriteService.updateTopic(topicDTO));
    assertThat(topicNotFoundException.getMessage()).isEqualTo("Nie znalaziono tematu o id = 1");
  }

}