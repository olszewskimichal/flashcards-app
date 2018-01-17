package pl.michal.olszewski.flashcardsapp.exam.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.michal.olszewski.flashcardsapp.exam.read.dto.ExamDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;


@ExtendWith(MockitoExtension.class)
class ExamReadObjectMapperTest {

  private ExamReadObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ExamReadObjectMapper();
  }

  @Test
  void shouldConvertTestToTestDTO() {
    Exam exam = Exam.builder().topic(Topic.builder().id(2L).build()).build();

    ExamDTO examDTO = mapper.convertToDTO(exam);
    assertThat(examDTO).isNotNull();
    assertThat(examDTO.getTopicId()).isEqualTo(2L);
    //assertThat(createExamDTO.getAttempts()).hasSize(1);
  }

}