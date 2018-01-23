package pl.michal.olszewski.flashcardsapp.exam.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.michal.olszewski.flashcardsapp.exam.read.dto.ExamDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.exam.ExamFactory;


@ExtendWith(MockitoExtension.class)
class ExamReadObjectMapperTest {

  private ExamReadObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ExamReadObjectMapper();
  }

  @Test
  void shouldConvertTestToTestDTO() {
    Exam exam = ExamFactory.build(1L, 2L);

    ExamDTO examDTO = mapper.convertToDTO(exam);
    assertThat(examDTO).isNotNull();
    assertThat(examDTO.getTopicId()).isEqualTo(2L);
  }

}