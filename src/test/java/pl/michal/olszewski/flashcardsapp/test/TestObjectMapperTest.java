package pl.michal.olszewski.flashcardsapp.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;


@ExtendWith(MockitoExtension.class)
class TestObjectMapperTest {

  @Mock
  private TopicRepository topicRepository;

  private TestObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new TestObjectMapper(topicRepository);
  }

  @Test
  void shouldConvertFromTestDTO() {
    TestDTO testDTO = TestDTO.builder().topicId(1L).build();
    given(topicRepository.findOne(1L)).willReturn(Topic.builder().id(1L).build());

    pl.michal.olszewski.flashcardsapp.test.Test test = mapper.convertFromDTO(testDTO);

    assertThat(test).isNotNull();
    assertThat(test.getTopic()).isNotNull();
  }

  @Test
  void shouldConvertTestToTestDTO() {
    pl.michal.olszewski.flashcardsapp.test.Test test = pl.michal.olszewski.flashcardsapp.test.Test.builder().topic(Topic.builder().id(2L).build()).build();

    TestDTO testDTO = mapper.convertToDTO(test);
    assertThat(testDTO).isNotNull();
    assertThat(testDTO.getTopicId()).isEqualTo(2L);
    //assertThat(testDTO.getAttempts()).hasSize(1);
  }

  @Test
  void shouldUpdateTestFromTestDTO() {
    pl.michal.olszewski.flashcardsapp.test.Test test = pl.michal.olszewski.flashcardsapp.test.Test.builder().build();
    TestDTO testDTO = TestDTO.builder().topicId(1L).build();
    test = mapper.updateFrom(testDTO, test);
    assertThat(test).isNotNull();
  }
}