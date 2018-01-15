package pl.michal.olszewski.flashtopicsapp.topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;
import pl.michal.olszewski.flashcardsapp.topic.TopicService;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

  private TopicService topicService;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private ObjectMapper<Topic, TopicDTO> objectMapper;

  @BeforeEach
  void setUp() {
    topicService = new TopicService(topicRepository, objectMapper);
  }

  @Test
  void shouldCreateNewTopic() {
    TopicDTO topicDTO = TopicDTO.builder().build();
    given(objectMapper.convertFromDTO(topicDTO)).willReturn(Topic.builder().build());
    Topic topic = topicService.createTopic(topicDTO);

    assertThat(topic).isNotNull();
    verify(topicRepository, times(1)).save(topic);
  }

  @Test
  void shouldUpdateTopic() {
    TopicDTO topicDTO = TopicDTO.builder().id(1L).name("new Name").build();
    Topic topic = Topic.builder().id(1L).name("name").build();
    given(topicRepository.findOne(1L)).willReturn(topic);
    given(objectMapper.updateFrom(topicDTO, topic)).willReturn(Topic.builder().build());

    Topic updateTopic = topicService.updateTopic(topicDTO);
    assertThat(updateTopic).isNotNull();
    verify(topicRepository, times(1)).findOne(1L);
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingTopic() {
    TopicDTO topicDTO = TopicDTO.builder().name("").id(1L).build();
    given(topicRepository.findOne(1L)).willReturn(null);

    TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> topicService.updateTopic(topicDTO));
    assertThat(topicNotFoundException.getMessage()).isEqualTo("Nie znalaziono tematu o id = 1");
  }

  @Test
  void shouldReturnTopicDTOById() {
    Topic topic = Topic.builder().id(2L).build();
    given(topicRepository.findOne(2L)).willReturn(topic);
    given(objectMapper.convertToDTO(topic)).willReturn(TopicDTO.builder().id(2L).build());
    TopicDTO topicDTO = topicService.getTopicById(2L);

    assertThat(topicDTO).isNotNull();
    assertThat(topicDTO.getId()).isEqualTo(2L);
    verify(topicRepository, times(1)).findOne(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(topicRepository.findOne(1L)).willReturn(null);
    TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> topicService.getTopicById(2L));
    assertThat(topicNotFoundException.getMessage()).isEqualTo("Nie znalaziono tematu o id = 2");
  }
}