package pl.michal.olszewski.flashcardsapp.attempt.read;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.michal.olszewski.flashcardsapp.attempt.read.dto.AttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.user.read.User;


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
    Attempt attempt = Attempt.builder()
        .id(1L)
        .user(User.builder().id(2L).build())
        .exam(Exam.builder().id(3L).build())
        .startDateTime(Instant.now())
        .attemptStatus(AttemptStatusEnum.DONE)
        .attemptCount(1L)
        .build();
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