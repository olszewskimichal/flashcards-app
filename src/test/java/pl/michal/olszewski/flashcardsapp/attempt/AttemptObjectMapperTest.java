package pl.michal.olszewski.flashcardsapp.attempt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.read.dto.AttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.readmodel.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.exam.writemodel.ExamRepository;
import pl.michal.olszewski.flashcardsapp.time.DateTimeService;
import pl.michal.olszewski.flashcardsapp.user.User;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;


@ExtendWith(MockitoExtension.class)
class AttemptObjectMapperTest {

  @Mock
  private ExamRepository examRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private DateTimeService timeService;

  private AttemptObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new AttemptObjectMapper(userRepository, examRepository, timeService);
  }

  @Test
  void shouldConvertFromDTO() {
    //given
    NewAttemptDTO newAttemptDTO = NewAttemptDTO.builder()
        .attemptCount(1L)
        .testId(2L)
        .userId(3L)
        .build();
    given(userRepository.findOne(3L)).willReturn(User.builder().build());
    given(examRepository.findOne(2L)).willReturn(Exam.builder().build());
    //when
    Attempt attempt = mapper.convertFromDTO(newAttemptDTO);
    //then
    assertThat(attempt).isNotNull();
    assertThat(attempt.getAttemptCount()).isEqualTo(1L);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.NEW.getValue());
    assertThat(attempt.getExam()).isNotNull();
    assertThat(attempt.getUser()).isNotNull();
  }

  @Test
  void shouldThrowExceptionWhenConvertFromDTOWithoutUserId() {
    //given
    NewAttemptDTO newAttemptDTO = NewAttemptDTO.builder()
        .attemptCount(1L)
        .testId(2L)
        .build();
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(newAttemptDTO));
    assertThat(exception).hasMessage("Nie podano id uzytkownika");
  }

  @Test
  void shouldThrowExceptionWhenConvertFromDTOWithoutTestId() {
    //given
    NewAttemptDTO newAttemptDTO = NewAttemptDTO.builder()
        .attemptCount(1L)
        .userId(2L)
        .build();
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(newAttemptDTO));
    assertThat(exception).hasMessage("Nie podano id testu");
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
    assertThat(attemptDTO.getTestId()).isNotNull().isEqualTo(3L);
    assertThat(attemptDTO.getUserId()).isNotNull().isEqualTo(2L);
    assertThat(attemptDTO.getAttemptCount()).isEqualTo(1L);
    assertThat(attemptDTO.getStartDateTime()).isNotNull();
    assertThat(attemptDTO.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
  }

  @Test
  void shouldUpdateFrom() {
    //given
    Attempt attempt = Attempt.builder().id(1L)
        .exam(Exam.builder().id(2L).build())
        .user(User.builder().id(4L).build())
        .attemptCount(1L)
        .attemptStatus(AttemptStatusEnum.DURING)
        .build();
    //when
    UpdateStatusAttemptDTO updateStatusAttemptDTO = UpdateStatusAttemptDTO.builder()
        .attemptId(1L)
        .attemptStatus(AttemptStatusEnum.DONE)
        .build();
    //then
    attempt = mapper.updateFrom(updateStatusAttemptDTO, attempt);
    assertThat(attempt).isNotNull();
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
  }
}