package pl.michal.olszewski.flashcardsapp.attempt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.AttemptService;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.readmodel.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;

@ExtendWith(MockitoExtension.class)
class AttemptServiceTest {

  private AttemptService attemptService;

  @Mock
  private AttemptObjectMapper objectMapper;

  @Mock
  private AttemptRepository attemptRepository;

  @BeforeEach
  void setUp() {
    attemptService = new AttemptService(objectMapper, attemptRepository);
  }

  @Test
  void shouldCreateNewAttempt() {
    NewAttemptDTO newAttemptDTO = NewAttemptDTO.builder().attemptCount(1L).testId(1L).userId(1L).build();
    given(objectMapper.convertFromDTO(Matchers.any(NewAttemptDTO.class)))
        .willReturn(Attempt.builder().exam(Exam.builder().topic(Topic.builder().build()).build()).build());

    Attempt attempt = attemptService.createNewAttempt(newAttemptDTO);

    assertThat(attempt).isNotNull();
    assertThat(attempt.getExam().getAttempts()).isNotEmpty();

    Mockito.verify(attemptRepository, Mockito.times(1)).save(attempt);
  }

  @Test
  void shouldCloseAttempt() {
    CloseAttemptDTO closeAttemptDTO = CloseAttemptDTO.builder().attemptId(1L).build();
    given(attemptRepository.findOne(1L)).willReturn(Attempt.builder().build());

    Attempt attempt = attemptService.closeAttempt(closeAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findOne(1L);
  }

  @Test
  void shouldUpdateAttemptStatus(){
    UpdateStatusAttemptDTO statusAttemptDTO=UpdateStatusAttemptDTO.builder().attemptStatus(AttemptStatusEnum.DONE).attemptId(1L).build();
    given(attemptRepository.findOne(1L)).willReturn(Attempt.builder().build());
    Attempt attempt = attemptService.updateAttemptStatus(statusAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findOne(1L);
  }
  
}