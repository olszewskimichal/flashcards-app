package pl.michal.olszewski.flashcardsapp.exam.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;


@ExtendWith(MockitoExtension.class)
class ExamWriteObjectMapperTest {

  @Mock
  private TopicRepository topicRepository;

  private ExamWriteObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ExamWriteObjectMapper(topicRepository);
  }

  @Test
  void shouldConvertFromTestDTO() {
    CreateExamDTO createExamDTO = CreateExamDTO.builder().topicId(1L).build();
    given(topicRepository.findOne(1L)).willReturn(Topic.builder().id(1L).build());

    Exam exam = mapper.convertFromDTO(createExamDTO);

    assertThat(exam).isNotNull();
    assertThat(exam.getTopic()).isNotNull();
  }
}