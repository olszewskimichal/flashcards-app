package pl.michal.olszewski.flashcardsapp.attempt.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.michal.olszewski.flashcardsapp.attempt.read.dto.AttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.attempt.AttemptFactory;


@ExtendWith(MockitoExtension.class)
class AttemptReadObjectMapperTest {

  private AttemptReadObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new AttemptReadObjectMapper();
  }

  @Test
  void shouldConvertToDTO() {
    //given
    Attempt attempt = AttemptFactory.buildAttemptWithUserAndExam(1L, 2L, 3L);
    //when
    AttemptDTO attemptDTO = mapper.convertToDTO(attempt);
    //then
    assertThat(attemptDTO).isNotNull();
    assertThat(attemptDTO.getExamId()).isNotNull().isEqualTo(3L);
    assertThat(attemptDTO.getUserId()).isNotNull().isEqualTo(2L);
    assertThat(attemptDTO.getAttemptCount()).isEqualTo(1L);
    assertThat(attemptDTO.getStartDateTime()).isNotNull();
    assertThat(attemptDTO.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
  }
}