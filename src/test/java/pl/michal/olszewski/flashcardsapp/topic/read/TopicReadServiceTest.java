package pl.michal.olszewski.flashcardsapp.topic.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicFactory;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@ExtendWith(MockitoExtension.class)
class TopicReadServiceTest {

  private TopicReadService topicReadService;

  @Mock
  private TopicFinder topicRepository;

  @Mock
  private ReadObjectMapper<Topic, TopicDTO> readObjectMapper;

  @BeforeEach
  void setUp() {
    topicReadService = new TopicReadService(topicRepository, readObjectMapper);
  }

  @Test
  void shouldReturnTopicDTOById() {
    Topic topic = TopicFactory.build(2L, "");
    given(topicRepository.findById(2L)).willReturn(Optional.of(topic));
    given(readObjectMapper.convertToDTO(topic)).willReturn(TopicDTO.builder().id(2L).build());
    TopicDTO topicDTO = topicReadService.getTopicById(2L);

    assertThat(topicDTO).isNotNull();
    assertThat(topicDTO.getId()).isEqualTo(2L);
    verify(topicRepository, times(1)).findById(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(topicRepository.findById(2L)).willReturn(Optional.empty());
    TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> topicReadService.getTopicById(2L));
    assertThat(topicNotFoundException.getMessage()).isEqualTo("Nie znalaziono tematu o id = 2");
  }
}