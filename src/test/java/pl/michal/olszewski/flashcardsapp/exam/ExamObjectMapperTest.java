package pl.michal.olszewski.flashcardsapp.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.exam.readmodel.Exam;
import pl.michal.olszewski.flashcardsapp.exam.writemodel.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;


@ExtendWith(MockitoExtension.class)
class ExamObjectMapperTest {

  @Mock
  private TopicRepository topicRepository;

  private ExamObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ExamObjectMapper(topicRepository);
  }

  @Test
  void shouldConvertFromTestDTO() {
    CreateExamDTO createExamDTO = CreateExamDTO.builder().topicId(1L).build();
    given(topicRepository.findOne(1L)).willReturn(Topic.builder().id(1L).build());

    Exam exam = mapper.convertFromDTO(createExamDTO);

    assertThat(exam).isNotNull();
    assertThat(exam.getTopic()).isNotNull();
  }

  @Test
  void shouldConvertTestToTestDTO() {
    Exam exam = Exam.builder().topic(Topic.builder().id(2L).build()).build();

    CreateExamDTO createExamDTO = mapper.convertToDTO(exam);
    assertThat(createExamDTO).isNotNull();
    assertThat(createExamDTO.getTopicId()).isEqualTo(2L);
    //assertThat(createExamDTO.getAttempts()).hasSize(1);
  }

  @Test
  void shouldUpdateTestFromTestDTO() {
    Exam exam = Exam.builder().build();
    CreateExamDTO createExamDTO = CreateExamDTO.builder().topicId(1L).build();
    exam = mapper.updateFrom(createExamDTO, exam);
    assertThat(exam).isNotNull();
  }
}