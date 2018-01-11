package pl.michal.olszewski.flashcardsapp.attempt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.attempt.dto.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;

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
    given(objectMapper.convertFromDTO(Matchers.any(NewAttemptDTO.class))).willReturn(Attempt.builder().build());

    Attempt attempt = attemptService.createNewAttempt(newAttemptDTO);

    assertThat(attempt).isNotNull();

    Mockito.verify(attemptRepository, Mockito.times(1)).save(attempt);
  }

  @Test
  void shouldCloseAttempt() {
    CloseAttemptDTO closeAttemptDTO = CloseAttemptDTO.builder().attemptId(1L).build();
    given(attemptRepository.findOne(1L)).willReturn(Attempt.builder().build());

    Attempt attempt = attemptService.closeAttempt(closeAttemptDTO);
    assertThat(attempt).isNotNull();
    Mockito.verify(attemptRepository, Mockito.times(1)).findOne(1L);
  }


}