package pl.michal.olszewski.flashcardsapp.attempt.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.exam.ExamRepository;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.attempt.CreateAttemptDTOFactory;
import pl.michal.olszewski.flashcardsapp.time.DateTimeService;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;


@ExtendWith(MockitoExtension.class)
class AttemptWriteObjectMapperTest {

  @Mock
  private ExamRepository examRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private DateTimeService timeService;

  private AttemptWriteObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new AttemptWriteObjectMapper(userRepository, examRepository, timeService);
  }

  @Test
  void shouldConvertFromDTO() {
    //given
    CreateAttemptDTO createAttemptDTO = CreateAttemptDTOFactory.buildWithUserAndExam(3L, 2L);
    given(userRepository.findOne(3L)).willReturn(User.builder().build());
    given(examRepository.findOne(2L)).willReturn(Exam.builder().build());
    //when
    Attempt attempt = mapper.convertFromDTO(createAttemptDTO);
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
    CreateAttemptDTO createAttemptDTO = CreateAttemptDTOFactory.buildWithUserAndExam(null, 2L);
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(createAttemptDTO));
    assertThat(exception).hasMessage("Nie podano id uzytkownika");
  }

  @Test
  void shouldThrowExceptionWhenConvertFromDTOWithoutTestId() {
    //given
    CreateAttemptDTO createAttemptDTO = CreateAttemptDTOFactory.buildWithUserAndExam(2L, null);
    //when
    //then
    NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.convertFromDTO(createAttemptDTO));
    assertThat(exception).hasMessage("Nie podano id testu");
  }

}