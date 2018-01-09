package pl.michal.olszewski.flashcardsapp.attempt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.test.TestRepository;
import pl.michal.olszewski.flashcardsapp.user.User;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;


@ExtendWith(MockitoExtension.class)
class AttemptObjectMapperTest {

  @Mock
  private TestRepository testRepository;

  @Mock
  private UserRepository userRepository;

  private AttemptObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new AttemptObjectMapper(userRepository, testRepository);
  }

  @Test
  void shouldConvertFromDTO() {
    //given
    AttemptDTO attemptDTO = AttemptDTO.builder()
        .attemptCount(1L)
        .attemptStatus(AttemptStatusEnum.NEW)
        .testId(2L)
        .userId(3L)
        .startDateTime(Instant.now())
        .build();
    given(userRepository.findOne(3L)).willReturn(User.builder().build());
    given(testRepository.findOne(2L)).willReturn(pl.michal.olszewski.flashcardsapp.test.Test.builder().build());
    //when
    Attempt attempt = mapper.convertFromDTO(attemptDTO);
    //then
    assertThat(attempt).isNotNull();
    assertThat(attempt.getAttemptCount()).isEqualTo(1L);
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.NEW.getValue());
    assertThat(attempt.getTest()).isNotNull();
    assertThat(attempt.getUser()).isNotNull();
  }

  @Test
  void shouldThrowExceptionWhenConvertFromDTOWithoutUserId() {
    //given
    AttemptDTO attemptDTO = AttemptDTO.builder()
        .attemptCount(1L)
        .attemptStatus(AttemptStatusEnum.NEW)
        .testId(2L)
        .startDateTime(Instant.now())
        .build();
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(attemptDTO));
    assertThat(exception).hasMessage("Nie podano id uzytkownika");
  }

  @Test
  void shouldThrowExceptionWhenConvertFromDTOWithoutTestId() {
    //given
    AttemptDTO attemptDTO = AttemptDTO.builder()
        .attemptCount(1L)
        .attemptStatus(AttemptStatusEnum.NEW)
        .userId(2L)
        .startDateTime(Instant.now())
        .build();
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(attemptDTO));
    assertThat(exception).hasMessage("Nie podano id testu");
  }


  @Test
  void shouldConvertToDTO() {
    //given
    Attempt attempt = Attempt.builder()
        .id(1L)
        .user(User.builder().id(2L).build())
        .test(pl.michal.olszewski.flashcardsapp.test.Test.builder().id(3L).build())
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
    Attempt attempt = Attempt.builder().id(1L).test(pl.michal.olszewski.flashcardsapp.test.Test.builder().id(2L).build())
        .user(User.builder().id(4L).build())
        .attemptCount(1L)
        .attemptStatus(AttemptStatusEnum.DURING)
        .build();
    //when
    AttemptDTO attemptDTO = AttemptDTO.builder()
        .id(1L)
        .attemptStatus(AttemptStatusEnum.DONE)
        .build();
    //then
    attempt = mapper.updateFrom(attemptDTO, attempt);
    assertThat(attempt).isNotNull();
    assertThat(attempt.getAttemptStatus()).isEqualTo(AttemptStatusEnum.DONE.getValue());
  }
}