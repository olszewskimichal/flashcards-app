package pl.michal.olszewski.flashcardsapp.attempt.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@ExtendWith(MockitoExtension.class)
class AttemptWriteServiceTest {

  private AttemptWriteService attemptWriteService;

  @Mock
  private AttemptWriteObjectMapper objectMapper;

  @Mock
  private AttemptRepository attemptRepository;

  @BeforeEach
  void setUp() {
    attemptWriteService = new AttemptWriteService(objectMapper, attemptRepository);
  }

  @Test
  void shouldCreateNewAttempt() {
    CreateAttemptDTO createAttemptDTO = CreateAttemptDTO.builder().attemptCount(1L).examId(1L).userId(1L).build();
    given(objectMapper.convertFromDTO(Matchers.any(CreateAttemptDTO.class)))
        .willReturn(Attempt.builder().exam(Exam.builder().topic(Topic.builder().build()).build()).build());

    Attempt attempt = attemptWriteService.createNewAttempt(createAttemptDTO);

    assertThat(attempt).isNotNull();
    assertThat(attempt.getExam().getAttempts()).isNotEmpty();

    Mockito.verify(attemptRepository, Mockito.times(1)).save(attempt);
  }

  @Test
  void shouldCloseAttempt() {
    CloseAttemptDTO closeAttemptDTO = CloseAttemptDTO.builder().attemptId(1L).build();
    given(attemptRepository.findOne(1L)).willReturn(Attempt.builder().build());

    Attempt attempt = attemptWriteService.closeAttempt(closeAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findOne(1L);
  }

  @Test
  void shouldUpdateAttemptStatus() {
    UpdateStatusAttemptDTO statusAttemptDTO = UpdateStatusAttemptDTO.builder().attemptStatus(AttemptStatusEnum.DONE).attemptId(1L).build();
    given(attemptRepository.findOne(1L)).willReturn(Attempt.builder().build());
    Attempt attempt = attemptWriteService.updateAttemptStatus(statusAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findOne(1L);
  }

}