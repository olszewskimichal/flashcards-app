package pl.michal.olszewski.flashcardsapp.attempt.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptNotFoundException;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.attempt.CloseAttemptDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.attempt.CreateAttemptDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.attempt.UpdateStatusAttemptDTOFactory;
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
    CreateAttemptDTO createAttemptDTO = CreateAttemptDTOFactory.buildWithUserAndExam(2L, 1L);

    given(objectMapper.convertFromDTO(Matchers.any(CreateAttemptDTO.class)))
        .willReturn(Attempt.builder().exam(Exam.builder().topic(Topic.builder().build()).build()).build());

    Attempt attempt = attemptWriteService.createNewAttempt(createAttemptDTO);

    assertThat(attempt).isNotNull();
    assertThat(attempt.getExam().getAttempts()).isNotEmpty();

    Mockito.verify(attemptRepository, Mockito.times(1)).save(attempt);
  }

  @Test
  void shouldCloseAttempt() {
    CloseAttemptDTO closeAttemptDTO = CloseAttemptDTOFactory.build(1L);
    given(attemptRepository.findById(1L)).willReturn(Optional.of(Attempt.builder().build()));

    Attempt attempt = attemptWriteService.closeAttempt(closeAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldThrowExceptionWhenTryCloseNotExistingAttempt() {
    CloseAttemptDTO closeAttemptDTO = CloseAttemptDTOFactory.build(1L);
    given(attemptRepository.findById(1L)).willReturn(Optional.empty());

    assertThrows(AttemptNotFoundException.class, () -> attemptWriteService.closeAttempt(closeAttemptDTO));
  }

  @Test
  void shouldUpdateAttemptStatus() {
    UpdateStatusAttemptDTO statusAttemptDTO = UpdateStatusAttemptDTOFactory.build(1L, AttemptStatusEnum.DONE);
    given(attemptRepository.findById(1L)).willReturn(Optional.of(Attempt.builder().build()));
    Attempt attempt = attemptWriteService.updateAttemptStatus(statusAttemptDTO);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldThrowExceptionWhenTryUpdateNotExistingAttempt() {
    UpdateStatusAttemptDTO statusAttemptDTO = UpdateStatusAttemptDTOFactory.build(1L, AttemptStatusEnum.DONE);
    given(attemptRepository.findById(1L)).willReturn(Optional.empty());

    assertThrows(AttemptNotFoundException.class, () -> attemptWriteService.updateAttemptStatus(statusAttemptDTO));
  }

}